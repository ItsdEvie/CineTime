package com.Spring.Cinetime.Exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long message) {
      super("Usuario não encontrado");
    }

    public UsuarioNotFoundException(String message) {
        super("Usuario não encontrado");
    }
}
