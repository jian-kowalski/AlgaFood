package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import lombok.var;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
    var jpql = new StringBuilder();

    var parametros = new HashMap<String, Object>();

    jpql.append("from Restaurante where 0=0 ");

    if (StringUtils.hasLength(nome)) {
      jpql.append("and nome like :nome ");
      parametros.put("nome", "%" + nome + "%");
    }

    if (taxaFreteInicial != null) {
      jpql.append("and taxaFrete >= :taxaInicial ");
      parametros.put("taxaInicial", taxaFreteInicial);
    }

    if (taxaFreteFinal != null) {
      jpql.append("and taxaFrete >= :taxaFreteFinal");
      parametros.put("taxaFreteFinal", taxaFreteFinal);
    }

    TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);

    parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

    return query.getResultList();

  }

  public List<Restaurante> procurarPorNomeCozinhaTaxa(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
      BigDecimal taxaFreteFinal) {

    var builder = manager.getCriteriaBuilder();
    var criteria = builder.createQuery(Restaurante.class);
    var root = criteria.from(Restaurante.class);
    var predicates = new ArrayList<Predicate>();

    if (StringUtils.hasLength(nome)) {
      predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
    }

    if (taxaFreteInicial != null) {
      predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
    }

    if (taxaFreteFinal != null) {
      predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
    }

    if (cozinhaId != null) {
      predicates.add(builder.equal(root.get("cozinha"),  + cozinhaId));
    }

    criteria.where(predicates.toArray(new Predicate[0]));
    return manager.createQuery(criteria).getResultList();

  }
}
