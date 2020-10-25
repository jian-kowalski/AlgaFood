package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

  private static final long serialVersionUID = 1L;

  public FormaPagamentoNaoEncontradaException(String mensagem){
      super(mensagem);
  }
  public FormaPagamentoNaoEncontradaException(Long formaPagamentoId){
      super(String.format("Forma de pagamento não encontrada para o Código %d", formaPagamentoId));
  }
  
}