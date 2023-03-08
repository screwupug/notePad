package views;

import controllers.LoggerController;
import controllers.NoteController;
import logger.Logger;
import model.Note;

import java.util.List;
import java.util.Scanner;

public class ViewNote {

    private NoteController noteController;
    private LoggerController loggerController;

    public ViewNote(NoteController noteController, LoggerController loggerController) {
        this.noteController = noteController;
        this.loggerController = loggerController;
    }

    public void run() {
        Commands com;
        while (true) {
            try {
                System.out.print("Основное меню\n");
                String command = prompt("Введите команду (введите help для просмтора доступных команд): ");
                com = Commands.valueOf(command.toUpperCase());
                switch (com) {
                    case EXIT -> System.exit(0);
                    case CREATE -> createNote();
                    case DELETE -> deleteNote();
                    case LIST -> showAllNotes();
                    case READ -> showOneUser();
                    case HELP -> System.out.println(Strings.MAIN_MENU);
                    case UPDATE -> {
                        checkBase();
                        updateUserInfo();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void showOneUser() {
        try {
            checkBase();
            String id = prompt("Идентификатор заметки: ");
            Note note = noteController.readNote(id);
            System.out.println(note);
            createLog(String.format("note %s was shown", id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateUserInfo() {
        Commands com;
        try {
            System.out.println("Меню изменения заметки\n");
            String command = prompt("Введите команду (введите help для просмтора доступных команд): ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case HEADER -> updateHeader();
                case BODY -> updateBody();
                case HELP -> {
                    System.out.println(Strings.UPDATE_MENU);
                    updateUserInfo();
                }
                case BACK -> run();
            }
        } catch (Exception e) {
            System.out.printf("Something wrong - %s\n", e.getMessage());
        }
    }

    private void updateBody() {
        String id = prompt("Введите идентификатор: ");
        String newBody = prompt("Введите новый текст заметки: ");
        try {
            noteController.updateNote(id, newBody, 2);
            System.out.println("Success\n");
            createLog(String.format("note %s - body updated", id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateHeader() {
        String id = prompt("Введите идентификатор: ");
        String newHeader = prompt("Введите новый заголовок заметки: ");
        try {
            noteController.updateNote(id, newHeader, 1);
            System.out.println("Success\n");
            createLog(String.format("note %s - header updated", id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllNotes() {
        try {
            checkBase();
            List<Note> notes = noteController.readAllNotes();
            for (Note note : notes) {
                System.out.println(note);
            }
            createLog("all notes were shown");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteNote() {
        try {
            checkBase();
            String id = prompt("Введите идентификатор: ");
            noteController.deleteNote(id);
            System.out.println("Success\n");
            createLog(String.format("note %s was deleted", id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createNote() {
        String header = prompt("Введите название заметки: ");
        String body =  prompt("Введите текст заметки: ");
        int id = noteController.createNote(new Note(header, body));
        System.out.println("Note created");
        createLog(String.format("Note %d was created", id));
    }

    private void checkBase() throws Exception {
        if (noteController.readAllNotes().isEmpty()) {
            throw new Exception("File is empty\n");
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private void createLog(String log) {
        loggerController.createLog(new Logger(log));
    }
}
