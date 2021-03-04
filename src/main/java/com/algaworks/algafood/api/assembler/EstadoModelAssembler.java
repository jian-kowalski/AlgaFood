package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.Model.EstadoModel;
import com.algaworks.algafood.domain.model.Estado;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstadoModelAssembler {


    public EstadoModel toModel(Estado estado) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(estado, EstadoModel.class);
    }

    public List<EstadoModel> toCollectionModel(List<Estado> estados) {
        return estados.stream().map(this::toModel).collect(Collectors.toList());
    }

}
