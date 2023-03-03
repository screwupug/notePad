package views;

import controllers.UserController;

import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;
        while (true) {
            System.out.print("Основное меню\n");
            String command = prompt("Введите команду (введите help для просмтора доступных команд): ");
            com = Commands.valueOf(command.toUpperCase());

        }
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
