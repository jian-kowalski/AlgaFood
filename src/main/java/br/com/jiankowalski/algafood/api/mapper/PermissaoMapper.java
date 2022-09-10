package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.PermissaoModel;
import br.com.jiankowalski.algafood.api.model.input.PermissaoInput;
import br.com.jiankowalski.algafood.domain.model.Permissao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface PermissaoMapper {
    List<PermissaoModel> toCollectionModel(List<Permissao> buscarPermissoes);

    PermissaoModel toModel(Permissao buscar);

    Permissao inputToDomain(PermissaoInput permissaoInput);

    void update(@MappingTarget Permissao permissaoAtual, PermissaoInput permissaoInput);
}
