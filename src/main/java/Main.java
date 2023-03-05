import controllers.UserController;
import model.*;
import views.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new DefaultFileOperation("notes.txt");
        Repository repository = new RepositoryOperation(fileOperation, new DefaultNoteMapper());
        UserController userController = new UserController(repository);
        ViewUser viewUser = new ViewUser(userController);
        viewUser.run();
    }
}
