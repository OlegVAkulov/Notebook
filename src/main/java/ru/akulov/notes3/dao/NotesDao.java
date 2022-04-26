package ru.akulov.notes3.dao;

import org.springframework.stereotype.Component;
import ru.akulov.notes3.models.Note;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class NotesDao {
    private static int NOTE_COUNT;
    private List<Note> notes;

    {
        notes = new ArrayList<>();
        notes.add(new Note(++NOTE_COUNT, "First note"));
    }

    public List<Note> index() {
        return notes;
    }

    public Note show(int id) {
        return notes.stream().filter(notes -> notes.getId() == id).findAny().orElse(null);
    }

    public void save(Note note) {
        note.setId(++NOTE_COUNT);
        notes.add(note);
    }

    public void update(int id, Note updateNote) {
        Note noteToBeUpdated = show(id);
        noteToBeUpdated.setNote(updateNote.getNote());
    }

    public void delete(int id) {
        notes.removeIf(p -> p.getId() == id);
    }

    public void saveToFile() throws IOException {
        FileWriter writer = new FileWriter("D:/notes.txt");
        for (int i = 0; i < notes.size(); i++) {
            writer.write(notes.get(i).getNote() + System.lineSeparator());
        }
        writer.close();
    }
}
