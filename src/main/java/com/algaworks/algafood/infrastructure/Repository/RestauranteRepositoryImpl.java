package com.algaworks.algafood.infrastructure.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
  
  @PersistenceContext
  private EntityManager manager;
  
  @Override
  public List<Restaurante> todos() {
      return manager.createQuery("from Restaurante", Restaurante.class)
              .getResultList();
  }
  
  @Override
  public Restaurante porId(Long id) {
      return manager.find(Restaurante.class, id);
  }
  
  @Transactional
  @Override
  public Restaurante adicionar(Restaurante restaurante) {
      return manager.merge(restaurante);
  }
  
  @Transactional
  @Override
  public void remover(Long id) {
      Restaurante restaurante = porId(id);
      if (restaurante == null){
        throw new EmptyResultDataAccessException(1);
      }
      manager.remove(restaurante);
  }

}  
