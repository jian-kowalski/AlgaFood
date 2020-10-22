package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository
        extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
    
    @Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();
            
    List<Restaurante> procurarPorNomeECozinha(String nome, @Param("id") Long cozinhaId);

}
