package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.CidadeModel;
import br.com.jiankowalski.algafood.api.model.input.CidadeInput;
import br.com.jiankowalski.algafood.domain.model.Cidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = EstadoMapper.class)
public interface CidadeMapper {

    List<CidadeModel> toCollectionModel(List<Cidade> cidades);

    CidadeModel toModel(Cidade cidade);

    Cidade inputToDomain(CidadeInput cidadeInput);

    @Mapping(target = "estado", source = "estado")
    void update(@MappingTarget Cidade cidadeAtual, CidadeInput cidadeInput);
}
