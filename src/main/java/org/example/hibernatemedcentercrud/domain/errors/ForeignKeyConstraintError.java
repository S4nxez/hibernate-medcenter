package org.example.hibernatemedcentercrud.domain.errors;

public class ForeignKeyConstraintError extends RuntimeException{
    public ForeignKeyConstraintError(String message) {
        super(message);
    }
}