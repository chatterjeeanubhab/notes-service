package com.myproject.notes_service.exception;
public class InvalidSortFieldException extends RuntimeException {
    public InvalidSortFieldException(String field) {
        super("Field \"" + field + "\" is not a valid sort field.");
    }
}