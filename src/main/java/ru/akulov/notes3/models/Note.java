package ru.akulov.notes3.models;

import jakarta.validation.constraints.NotEmpty;

public class Note {
    private int id;
    @NotEmpty(message = "Note should not be empty")
    private String note;

    public Note() {
    }

    public Note(int id, String note) {
        this.id = id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "\n" +"id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}
