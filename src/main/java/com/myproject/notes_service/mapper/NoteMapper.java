package com.myproject.notes_service.mapper;
import com.myproject.notes_service.dto.NoteResponse;
import com.myproject.notes_service.entity.Note;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
@Component
public class NoteMapper{
    public NoteResponse mapToResponse(Note note) {
        return note==null? null:new NoteResponse(note);
    }
    public List<NoteResponse> mapToResponseList(List<Note> notes) {
            if (notes == null) {
            return Collections.emptyList();
        }

        List<NoteResponse> noteResponses = new ArrayList<>();
        for(Note note : notes) {
            noteResponses.add(mapToResponse(note));
        }
        return noteResponses;
    }
}