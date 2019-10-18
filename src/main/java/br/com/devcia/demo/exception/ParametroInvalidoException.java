package br.com.devcia.demo.exception;

public class ParametroInvalidoException extends RuntimeException {
    public ParametroInvalidoException(final String message) {
        super(message);
    }
}