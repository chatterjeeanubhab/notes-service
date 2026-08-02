package com.myproject.notes_service.service;

import com.myproject.notes_service.dto.CreateNoteRequest;
import com.myproject.notes_service.dto.UpdateNoteRequest;
import com.myproject.notes_service.entity.Note;
import com.myproject.notes_service.repository.NoteRepository;
import com.myproject.notes_service.exception.NoteNotFoundException;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }
    public Note updateNote(Long id,UpdateNoteRequest request){
        Note note=noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());
        note.setCategory(request.getCategory());
        return noteRepository.save(note);
    }
    public void deleteNote(Long id){
         Note note=noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
         noteRepository.delete(note);
    }
    public List<Note> findByTitleIgnoreCase(String title) {
        return noteRepository.findByTitleContainingIgnoreCase(title);
    }
}