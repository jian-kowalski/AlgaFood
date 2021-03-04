package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.Model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssembler {

    public CozinhaModel toModel(Cozinha cozinha) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(cozinha, CozinhaModel.class);
    }

    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toModel).collect(Collectors.toList());
    }
}
