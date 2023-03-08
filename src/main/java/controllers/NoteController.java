package controllers;

import model.Note;
import model.Repository;

import java.util.Iterator;
import java.util.List;

public class NoteController {
    private Repository repository;

    public NoteController(Repository repository) {
        this.repository = repository;
    }

    public int createNote(Note note) {
       return repository.createNote(note);
    }

    public Note readNote(String noteId) throws Exception {
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                return note;
            }
        }
        throw new Exception("Note not found");
    }

    public List<Note> readAllNotes() {
        return repository.getAllNotes();
    }

    public Note deleteNote(String noteId) throws Exception {
        List<Note> notes = repository.getAllNotes();
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getId().equals(noteId)) {
                iterator.remove();
                repository.refreshRepository(notes);
                return note;
            }
        }
        throw new Exception("Note not found");
    }

    public Note updateNote(String noteId, String newInfo, int operation) throws Exception {
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                switch (operation) {
                    case 1 -> {
                        note.setHeader(newInfo);
                        repository.refreshRepository(notes);
                    }
                    case 2 -> {
                        note.setBody(newInfo);
                        repository.refreshRepository(notes);
                    }
                }
                return note;
            }
        }
        throw new Exception("Note not found");
    }
}
