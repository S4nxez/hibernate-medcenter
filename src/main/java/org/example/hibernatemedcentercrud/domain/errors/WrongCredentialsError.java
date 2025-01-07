package org.example.hibernatemedcentercrud.domain.errors;

public class WrongCredentialsError extends RuntimeException {
    public WrongCredentialsError(String message) {
        super(message);
    }
}
