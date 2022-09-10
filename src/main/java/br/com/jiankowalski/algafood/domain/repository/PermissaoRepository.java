package br.com.jiankowalski.algafood.domain.repository;

import br.com.jiankowalski.algafood.domain.model.Permissao;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {


}