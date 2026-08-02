package com.myproject.notes_service.controller;

import com.myproject.notes_service.dto.CreateNoteRequest;
import com.myproject.notes_service.dto.UpdateNoteRequest;
import com.myproject.notes_service.entity.Note;
import com.myproject.notes_service.service.NoteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @PostMapping("/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@Valid @RequestBody CreateNoteRequest request) {
       return noteService.createNote(request);
    }
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }
    @PutMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Note updateNote(@PathVariable Long id, @Valid @RequestBody UpdateNoteRequest request) {
        return noteService.updateNote(id, request);
    }
    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
    @GetMapping(value="/notes/search", params="title")
    public List<Note> findByTitle(@RequestParam String title) {
        return noteService.findByTitleIgnoreCase(title);
    }
     @GetMapping(value="/notes/search", params="category")
    public List<Note> findByCategory(@RequestParam String category) {
        return noteService.findByCategoryIgnoreCase(category);
    }
}