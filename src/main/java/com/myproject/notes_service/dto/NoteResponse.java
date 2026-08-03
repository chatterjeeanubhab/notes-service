package com.myproject.notes_service.dto;
import com.myproject.notes_service.entity.Note;
import java.time.LocalDateTime;
import lombok.Getter;
@Getter
public class NoteResponse{
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NoteResponse(Note note){
        this.id = note.getId();
        this.title = note.getTitle();
        this.description = note.getDescription();
        this.category = note.getCategory();
        this.createdAt = note.getCreatedAt();
        this.updatedAt = note.getUpdatedAt();
    }
}