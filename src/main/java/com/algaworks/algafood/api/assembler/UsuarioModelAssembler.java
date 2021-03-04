package com.algaworks.algafood.api.assembler;

import java.util.List;

import java.util.stream.Collectors;

import com.algaworks.algafood.api.Model.UsuarioModel;
import com.algaworks.algafood.domain.model.Usuario;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler {

    public UsuarioModel toModel(Usuario usuario) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toColletion(List<Usuario> usuarios) {
      return usuarios.stream().map(this::toModel).collect(Collectors.toList());
    }
    
}
