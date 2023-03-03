package model;

public interface NoteMapper {
    String map(Note note);
    Note map(String line);
}
