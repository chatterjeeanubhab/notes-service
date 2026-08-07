package com.myproject.notes_service.service;

import com.myproject.notes_service.dto.CreateNoteRequest;
import com.myproject.notes_service.dto.UpdateNoteRequest;
import com.myproject.notes_service.entity.Note;
import com.myproject.notes_service.exception.NoteNotFoundException;
import com.myproject.notes_service.exception.InvalidSortFieldException;
import com.myproject.notes_service.exception.InvalidSortFormatException;
import com.myproject.notes_service.exception.InvalidSortDirectionException;
import com.myproject.notes_service.repository.NoteRepository;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class NoteService {
    // Service methods for managing notes will be implemented here
     private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id",
            "title",
            "description",
            "category",
            "createdAt",
            "updatedAt"
    );
  private final NoteRepository noteRepository;
   public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public Note createNote(CreateNoteRequest request) {
      Note note=new Note(request.getTitle(), request.getDescription(), request.getCategory());
      return noteRepository.save(note);
    }
    public Page<Note> getAllNotes(int page,int size,String sort) {
          String[] field=sort.split(",");
          if(field.length!=2){
            throw new InvalidSortFormatException("Sort format is invalid. Expected format: field,direction");
          }
       if (!ALLOWED_SORT_FIELDS.contains(field[0])) {
        throw new InvalidSortFieldException(field[0]);
        }
      if(!field[1].equalsIgnoreCase("asc") && !field[1].equalsIgnoreCase("desc")){
            throw new InvalidSortDirectionException("Sort direction is invalid. Expected 'asc' or 'desc'");
        }
        return noteRepository.findAll(PageRequest.of(page, size).withSort(Sort.Direction.fromString(field[1]), field[0]));
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
    public List<Note> findByCategoryIgnoreCase(String category){
        return noteRepository.findByCategoryContainingIgnoreCase(category);
    }
}