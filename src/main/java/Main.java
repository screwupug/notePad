import controllers.LoggerController;
import controllers.NoteController;
import logger.model.LoggerFileOperation;
import logger.model.LoggerRepository;
import logger.model.Mapper;
import model.*;
import views.ViewNote;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new DefaultFileOperation("notes.txt");
        Repository repository = new RepositoryOperation(fileOperation, new DefaultNoteMapper());
        NoteController noteController = new NoteController(repository);
        LoggerFileOperation loggerFileOperation = new logger.model.FileOperation("logs.txt");
        LoggerRepository loggerRepository = new logger.model.Repository(new Mapper(), loggerFileOperation);
        LoggerController loggerController = new LoggerController(loggerRepository);
        ViewNote viewNote = new ViewNote(noteController, loggerController);
        viewNote.run();

    }
}
