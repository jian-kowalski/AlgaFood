package br.com.jiankowalski.algafood.domain.repository;

import br.com.jiankowalski.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);
}
