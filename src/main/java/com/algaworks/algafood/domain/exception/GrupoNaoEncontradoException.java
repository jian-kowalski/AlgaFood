package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GrupoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
    public GrupoNaoEncontradoException(Long restauranteId){
        super(String.format("Grupo não encontrado para o código %d.", restauranteId));
    }
    
}
