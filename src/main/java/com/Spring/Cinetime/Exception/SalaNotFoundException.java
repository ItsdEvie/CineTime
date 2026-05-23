package com.Spring.Cinetime.Exception;

public class SalaNotFoundException extends RuntimeException {

    public SalaNotFoundException(Long id) {

        super("Sala não encontrada");

    }

    public SalaNotFoundException(String message) {

        super("Sala não encontrada");

    }

}