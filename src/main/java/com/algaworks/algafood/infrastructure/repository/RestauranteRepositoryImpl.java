package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

import org.springframework.stereotype.Repository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries{

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
    String jpql;
    jpql = "from Restaurante where nome like :nome " + "and taxaFrete between :taxaInicial and :taxaFreteFinal";
    return manager.createQuery(jpql, Restaurante.class).setParameter("nome", "%" + nome + "%")
        .setParameter("taxaInicial", taxaFreteInicial).setParameter("taxaFreteFinal", taxaFreteFinal).getResultList();
  }
}
