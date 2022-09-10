package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.CozinhaModel;
import br.com.jiankowalski.algafood.api.model.input.CozinhaIdInput;
import br.com.jiankowalski.algafood.api.model.input.CozinhaInput;
import br.com.jiankowalski.algafood.domain.model.Cozinha;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CozinhaMapper {
    List<CozinhaModel> toCollectionModel(List<Cozinha> buscarCozinhas);

    CozinhaModel toModel(Cozinha buscar);

    Cozinha inputToDomain(CozinhaInput cozinhaInput);

    Cozinha cozinhaIdInputToCozinha(CozinhaIdInput cozinhaIdInput);

    void update(@MappingTarget Cozinha cozinha, CozinhaInput cozinhaInput);
    
}
