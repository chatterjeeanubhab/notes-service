package com.myproject.notes_service.service;

import com.myproject.notes_service.dto.CreateNoteRequest;
import com.myproject.notes_service.entity.Note;
import com.myproject.notes_service.repository.NoteRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    // Service methods for managing notes will be implemented here
  private final NoteRepository noteRepository;
   public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public Note createNote(CreateNoteRequest request) {
      Note note=new Note(request.getTitle(), request.getDescription(), request.getCategory());
       return noteRepository.save(note);
    }
    public List<Note> getAllNotes(){
      return noteRepository.findAll();
    }
}