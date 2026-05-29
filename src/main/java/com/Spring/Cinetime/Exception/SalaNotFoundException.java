package com.Spring.Cinetime.Exception;

public class SalaNotFoundException extends RuntimeException {

    public SalaNotFoundException(Long id) {
        super("Sala não encontrada com ID: " + id);
    }

    public SalaNotFoundException(String message) {
        super(message);
    }
}