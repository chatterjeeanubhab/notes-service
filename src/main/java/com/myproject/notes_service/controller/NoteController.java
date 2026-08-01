package com.myproject.notes_service.controller;

import com.myproject.notes_service.dto.CreateNoteRequest;
import com.myproject.notes_service.entity.Note;
import com.myproject.notes_service.service.NoteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

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
}