package ru.akulov.notes3.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akulov.notes3.dao.NotesDao;
import ru.akulov.notes3.models.Note;

import java.io.IOException;

@Controller
@RequestMapping("/notes")
public class NotesController {


    private final NotesDao notesDao;

    @Autowired
    public NotesController(NotesDao notesDao) {
        this.notesDao = notesDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("notes", notesDao.index());
        return "notes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", notesDao.show(id));
        return "notes/show";
    }

    @GetMapping("/new")
    public String newNote(@ModelAttribute("note") Note note) {
        return "notes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("note") @Valid Note note) {
        notesDao.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("note", notesDao.show(id));
        return "notes/edit";

    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("note") Note note,
                         @PathVariable("id") int id){
        notesDao.update(id, note);
        return "redirect:/notes";

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        notesDao.delete(id);
        return "redirect:/notes";
    }
    @GetMapping("/saveToFile")
    public String save(Model model) throws IOException {
        model.addAttribute("notes", notesDao.index());
        notesDao.saveToFile();
        return "/notes/saveToFile";
    }
}
