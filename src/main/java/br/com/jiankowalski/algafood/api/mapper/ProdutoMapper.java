package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.ProdutoModel;
import br.com.jiankowalski.algafood.api.model.input.ProdutoInput;
import br.com.jiankowalski.algafood.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface ProdutoMapper {
    List<ProdutoModel> toColletion(List<Produto> buscarPorRestaurante);

    ProdutoModel toModel(Produto produto);

    Produto inputToDomain(ProdutoInput produtoInput);

    void update(@MappingTarget Produto produtoAtual, ProdutoInput produtoInput);
}
