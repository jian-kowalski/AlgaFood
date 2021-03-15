package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamantoRepository extends CustomJpaRepository<FormaPagamento, Long> {

}