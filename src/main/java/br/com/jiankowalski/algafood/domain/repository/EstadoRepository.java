package br.com.jiankowalski.algafood.domain.repository;

import br.com.jiankowalski.algafood.domain.model.Estado;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {


}