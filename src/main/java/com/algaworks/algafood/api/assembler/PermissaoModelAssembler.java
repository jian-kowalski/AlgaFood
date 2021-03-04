package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.Model.PermissaoModel;
import com.algaworks.algafood.domain.model.Permissao;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler {


    public PermissaoModel toModel(Permissao permissao) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(permissao, PermissaoModel.class);
    }

    public List<PermissaoModel> toCollectionModel(Collection<Permissao> permissoes) {
        return permissoes.stream().map(this::toModel).collect(Collectors.toList());
    }

}
