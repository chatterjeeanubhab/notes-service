package com.myproject.notes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateNoteRequest {
    private String title;
    private String description;
    private String category;
}