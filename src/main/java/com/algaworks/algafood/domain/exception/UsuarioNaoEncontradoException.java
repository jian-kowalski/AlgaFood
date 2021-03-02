package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeEmUsoException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long usuarioId){
        super(String.format("Usuario não encontrado para o código %d.", usuarioId));
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    
}
