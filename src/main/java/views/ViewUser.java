package views;

import controllers.UserController;
import model.Note;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
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
            String id = prompt("Идентификатор пользователя: ");
            Note note = userController.readNote(id);
            System.out.println(note);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateUserInfo() {
        Commands com;
        try {
            System.out.println("Меню изменения контакта\n");
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
            userController.updateNote(id, newBody, 2);
            System.out.println("Success\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateHeader() {
        String id = prompt("Введите идентификатор: ");
        String newHeader = prompt("Введите новый заголовок заметки: ");
        try {
            userController.updateNote(id, newHeader, 1);
            System.out.println("Success\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllNotes() {
        try {
            checkBase();
            List<Note> notes = userController.readAllNotes();
            for (Note note : notes) {
                System.out.println(note);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteNote() {
        try {
            checkBase();
            String id = prompt("Введите идентификатор: ");
            userController.deleteNote(id);
            System.out.println("Success\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createNote() {
        String header = prompt("Введите название заметки: ");
        String body =  prompt("Введите текст заметки: ");
        userController.createNote(new Note(header, body));
        System.out.println("Note created");
    }

    private void checkBase() throws Exception {
        if (userController.readAllNotes().isEmpty()) {
            throw new Exception("File is empty\n");
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
