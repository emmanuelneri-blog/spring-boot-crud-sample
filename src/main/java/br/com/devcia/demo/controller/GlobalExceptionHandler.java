package br.com.devcia.demo.controller;

import br.com.devcia.demo.exception.ParametroInvalidoException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String CODIGO_SQL_CHAVE_UNICA_VIOLADA = "23505";

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity handlerError(final ParametroInvalidoException piex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(piex.getMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handlerError(final EmptyResultDataAccessException erdaex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador não encontrado");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handlerError(final DataIntegrityViolationException divex) {
        final Throwable cause = divex.getCause();
        if (cause instanceof ConstraintViolationException) {
            final ConstraintViolationException cvex = (ConstraintViolationException) cause;
            if (cvex.getSQLState().equals(CODIGO_SQL_CHAVE_UNICA_VIOLADA)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chave única violada");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(divex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerError(final Exception ex) {
        log.error("Erro inesperado", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado");
    }
}
