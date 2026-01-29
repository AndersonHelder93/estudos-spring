package com.anderson.usuarios_api.exception;

import java.util.List;
import com.anderson.usuarios_api.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroResposta>> tratarValidacao(MethodArgumentNotValidException ex) {

        List<ErroResposta> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErroResposta(
                        HttpStatus.BAD_REQUEST.value(),
                        error.getField() + ": " + error.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(erros);
    }


    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {

        ErroResposta erros = new ErroResposta(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(erros);
    }


}
