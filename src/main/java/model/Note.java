package model;

public class Note {
    private String id;
    private String header;
    private String body;

    public Note(String header, String body) {
        this.header = header;
        this.body = body;
    }
    public Note(String id, String header, String body) {
        this.id = id;
        this.header = header;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор - %s\nНазвание - %s\n%s", id, header, body);
    }
}
