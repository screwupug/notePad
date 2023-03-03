package model;

import java.util.List;

public interface Repository {
        List<Note> getAllNotes();
        void createNote(Note note);
        void refreshRepository(List<Note> note);
}
