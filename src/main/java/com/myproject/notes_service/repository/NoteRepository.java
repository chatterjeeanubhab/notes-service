package com.myproject.notes_service.repository;

import com.myproject.notes_service.entity.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Repository methods for managing notes will be defined here
    List<Note> findByTitleContainingIgnoreCase(String title);
    List<Note> findByCategoryContainingIgnoreCase(String category);
}