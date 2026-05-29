package com.Spring.Cinetime.Exception;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(Long id) {
        super("Usuário não encontrado com ID: " + id);
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}