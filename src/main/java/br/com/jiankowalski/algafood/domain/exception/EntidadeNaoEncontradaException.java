package br.com.jiankowalski.algafood.domain.exception;


public class EntidadeNaoEncontradaException extends RuntimeException {

     public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}