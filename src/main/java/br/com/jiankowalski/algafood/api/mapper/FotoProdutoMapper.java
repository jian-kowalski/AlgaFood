package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.FotoProdutoModel;
import br.com.jiankowalski.algafood.domain.model.FotoProduto;
import org.mapstruct.Mapper;

@Mapper
public interface FotoProdutoMapper {

    FotoProdutoModel toModel(FotoProduto fotoSalva);

}
