package com.myproject.notes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateNoteRequest {
    @NotBlank
    @Size(max = 100)
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
}