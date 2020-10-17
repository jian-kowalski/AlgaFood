package com.algaworks.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;

import org.springframework.data.jpa.domain.Specification;


public class RestaurantesSpecs {

  public static Specification<Restaurante> comFreteGratis(){
    return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
  }
  
  public static Specification<Restaurante> comNomeSemelhante(String nome){
    if (StringUtils.hasLength(nome)) {
      return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
    } 
    return (root, query, builder) -> builder.isNotNull(root.get("nome"));
  }
        
}
