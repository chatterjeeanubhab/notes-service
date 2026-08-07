package com.myproject.notes_service.exception;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus; 
import com.myproject.notes_service.dto.ErrorResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.FieldError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e,HttpServletRequest request) {
     
     Map<String, String> errors = new HashMap<>();
     for(FieldError error : e.getBindingResult().getFieldErrors()) {
         errors.put(error.getField(), error.getDefaultMessage());
     }
        return new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            "Input validation failed",
            request.getRequestURI(), errors
        );
    }
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoteNotFoundException(NoteNotFoundException e,HttpServletRequest request){
        return new ErrorResponse(  LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Note Not Found",
            e.getMessage(),
            request.getRequestURI(), new HashMap<>()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e,HttpServletRequest request){
        return new ErrorResponse(  LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Argument",
            e.getMessage(),
            request.getRequestURI(), new HashMap<>()
        );
    }
    @ExceptionHandler(InvalidSortFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidSortFieldException(InvalidSortFieldException e,HttpServletRequest request){
            return new ErrorResponse(  LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Argument",
            e.getMessage(),
            request.getRequestURI(), new HashMap<>()
        );
    }
    @ExceptionHandler(InvalidSortFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidSortFormatException(InvalidSortFormatException e,HttpServletRequest request){
            return new ErrorResponse(  LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Argument",
            e.getMessage(),
            request.getRequestURI(), new HashMap<>()
        );
    }
    @ExceptionHandler(InvalidSortDirectionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidSortDirectionException(InvalidSortDirectionException e,HttpServletRequest request){
            return new ErrorResponse(  LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Argument",
            e.getMessage(),
            request.getRequestURI(), new HashMap<>()
        );
    }
}
