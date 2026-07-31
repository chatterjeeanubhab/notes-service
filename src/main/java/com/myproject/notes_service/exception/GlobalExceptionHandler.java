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

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Exception handling methods will be implemented here
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e) {
     
        return new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            "Input validation failed",
            "", 
            e.getBindingResult().getFieldErrors().stream()
                .collect(HashMap::new, (map, error) -> map.put(error.getField(), error.getDefaultMessage()), HashMap::putAll)
        );
    }
}