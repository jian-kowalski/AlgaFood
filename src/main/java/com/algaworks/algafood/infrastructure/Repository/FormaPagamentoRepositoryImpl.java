package com.algaworks.algafood.infrastructure.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamantoRepository;

public class FormaPagamentoRepositoryImpl implements FormaPagamantoRepository {

  @PersistenceContext
  private EntityManager manager;
        
  @Override
  public List<FormaPagamento> todas() {
      return manager.createQuery("from FormaPagamento", FormaPagamento.class)
              .getResultList();
  }
  
  @Override
  public FormaPagamento porId(Long id) {
      return manager.find(FormaPagamento.class, id);
  }
  
  @Transactional
  @Override
  public FormaPagamento adicionar(FormaPagamento formaPagamento) {
      return manager.merge(formaPagamento);
  }
  
  @Transactional
  @Override
  public void remover(FormaPagamento formaPagamento) {
      formaPagamento = porId(formaPagamento.getId());
      manager.remove(formaPagamento);
  }

}
