package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.Model.CidadeModel;
import com.algaworks.algafood.domain.model.Cidade;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CidadeModelAssembler {

    public CidadeModel toModel(Cidade cidade) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(cidade, CidadeModel.class);
    }
    
    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
