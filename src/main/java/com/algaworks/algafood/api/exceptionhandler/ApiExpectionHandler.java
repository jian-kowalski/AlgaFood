package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExpectionHandler {

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build());
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> tratarNegocioException(NegocioException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build());
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build());
  }

}
