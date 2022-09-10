package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.RestauranteModel;
import br.com.jiankowalski.algafood.api.model.RestauranteResumoModel;
import br.com.jiankowalski.algafood.api.model.input.RestauranteIdInput;
import br.com.jiankowalski.algafood.api.model.input.RestauranteInput;
import br.com.jiankowalski.algafood.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {CozinhaMapper.class, EnderecoMapper.class})
public interface RestauranteMapper {
    List<RestauranteModel> toCollectionModel(List<Restaurante> buscarRestaurantes);

    RestauranteModel toModel(Restaurante restaurante);

    Restaurante inputToDomain(RestauranteInput restauranteInput);

    @Mapping(target = "cozinha", source = "cozinha")
    @Mapping(target = "endereco", source = "endereco")
    void update(@MappingTarget Restaurante restauranteAtual, RestauranteInput restauranteInput);

    RestauranteResumoModel restauranteToRestauranteResumoModel(Restaurante restaurante);
    
    Restaurante restauranteIdInputToRestaurante(RestauranteIdInput restauranteIdInput);
}
