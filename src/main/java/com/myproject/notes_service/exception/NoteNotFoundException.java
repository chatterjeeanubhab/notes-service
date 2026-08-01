package com.myproject.notes_service.exception;

public class NoteNotFoundException extends RuntimeException{
     public NoteNotFoundException(Long id){
        super("Note with id " + id + " not found");
    }
}