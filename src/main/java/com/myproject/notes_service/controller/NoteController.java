package com.myproject.notes_service.controller;

import com.myproject.notes_service.dto.CreateNoteRequest;
import com.myproject.notes_service.dto.UpdateNoteRequest;
import com.myproject.notes_service.dto.NoteResponse;
import com.myproject.notes_service.service.NoteService;
import com.myproject.notes_service.mapper.NoteMapper;

import org.springframework.web.bind.annotation.RequestMapping;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;



@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final NoteMapper noteMapper;
    public NoteController(NoteService noteService, NoteMapper noteMapper) {
    this.noteService = noteService;
    this.noteMapper = noteMapper;
}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse createNote(@Valid @RequestBody CreateNoteRequest request) {
       return noteMapper.mapToResponse(noteService.createNote(request));
    }
    @GetMapping(params = {"page", "size","sort"})
    public Page<NoteResponse> getAllNotes(@RequestParam int page,@RequestParam int size,@RequestParam String sort) throws Exception {
        return noteMapper.mapToResponsePage(noteService.getAllNotes(page,size,sort));
    }
    @GetMapping("/{id}")
    public NoteResponse getNoteById(@PathVariable Long id) {
        return noteMapper.mapToResponse(noteService.getNoteById(id));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoteResponse updateNote(@PathVariable Long id, @Valid @RequestBody UpdateNoteRequest request) {
        return noteMapper.mapToResponse(noteService.updateNote(id, request));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
    @GetMapping(value="/search", params="title")
    public List<NoteResponse> findByTitle(@RequestParam String title) {
        return noteMapper.mapToResponseList(noteService.findByTitleIgnoreCase(title));
    }
     @GetMapping(value="/search", params="category")
    public List<NoteResponse> findByCategory(@RequestParam String category) {
        return noteMapper.mapToResponseList(noteService.findByCategoryIgnoreCase(category));
    }
}