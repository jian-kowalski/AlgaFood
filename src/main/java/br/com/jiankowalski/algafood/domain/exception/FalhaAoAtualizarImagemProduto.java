package br.com.jiankowalski.algafood.domain.exception;

public class FalhaAoAtualizarImagemProduto extends RuntimeException {
    public FalhaAoAtualizarImagemProduto(Exception e) {
        super(e);
    }
}
