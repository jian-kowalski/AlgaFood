package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.GrupoModel;
import br.com.jiankowalski.algafood.api.model.input.GrupoInput;
import br.com.jiankowalski.algafood.domain.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper
public interface GrupoMapper {
    List<GrupoModel> toCollectionModel(List<Grupo> buscarGrupos);

    GrupoModel toModel(Grupo buscar);

    Grupo inputToDomain(GrupoInput grupoInput);

    List<GrupoModel> toCollectionModel(Set<Grupo> grupos);

    void update(@MappingTarget Grupo grupo, GrupoInput grupoInput);
}
