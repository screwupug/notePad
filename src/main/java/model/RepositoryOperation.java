package model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryOperation implements Repository {
    private FileOperation fileOperation;
    private NoteMapper noteMapper;

    public RepositoryOperation(FileOperation fileOperation, NoteMapper noteMapper) {
        this.fileOperation = fileOperation;
        this.noteMapper = noteMapper;
    }

    @Override
    public List<Note> getAllNotes() {
        List<String> lines = fileOperation.readAllLines();
        List<Note> notes = new ArrayList<>();
        for (String line : lines) {
            notes.add(noteMapper.map(line));
        }
        return notes;
    }

    @Override
    public int createNote(Note note) {
        List<Note> notes = getAllNotes();
        List<String> lines = new ArrayList<>();
        int lastId = 0;
        for (Note item : notes) {
            int id = Integer.parseInt(item.getId());
            if (id > lastId) {
                lastId = id;
            }
        }
        int newId = lastId + 1;
        note.setId(String.valueOf(newId));
        notes.add(note);
        for (Note item : notes) {
            lines.add(noteMapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        return newId;
    }

    @Override
    public void refreshRepository(List<Note> notes) {
        List<String> lines = new ArrayList<>();
        for (Note item : notes) {
            lines.add(noteMapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }
}
