package com.algaworks.algafood.infrastructure.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {
  
  @PersistenceContext
  private EntityManager eManager;

  @Override
  @Transactional
  public Cozinha adicionar(Cozinha cozinha){
    return eManager.merge(cozinha);
  }

  @Override
  public Cozinha porId(Long id) {
    return eManager.find(Cozinha.class, id);
  }

  @Override
  @Transactional
  public void remover(Long id){
      Cozinha cozinha = porId(id);
      if (cozinha == null){
        throw new EmptyResultDataAccessException(1);
      }
      eManager.remove(cozinha);
  }

  @Override
  public List<Cozinha> todas() {
    TypedQuery<Cozinha> query = eManager.createQuery("from Cozinha", Cozinha.class);
    return query.getResultList();
  }

}