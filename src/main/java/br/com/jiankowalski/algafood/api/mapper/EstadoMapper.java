package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.EstadoModel;
import br.com.jiankowalski.algafood.api.model.input.EstadoIdInput;
import br.com.jiankowalski.algafood.api.model.input.EstadoInput;
import br.com.jiankowalski.algafood.domain.model.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface EstadoMapper {
    List<EstadoModel> toCollectionModel(List<Estado> buscarEstados);

    Estado iputToDomain(EstadoInput estadoInput);

    EstadoModel toModel(Estado adicionar);

    Estado estadoIdInputToEstado(EstadoIdInput estadoIdInput);

    void update(@MappingTarget Estado estadoAtual, EstadoInput estadoInput);
}
