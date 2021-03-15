package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.Model.UsuarioModel;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler {

    public UsuarioModel toModel(Usuario usuario) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toColletion(Collection<Usuario> usuarios) {
        return usuarios.stream().map(this::toModel).collect(Collectors.toList());
    }

}
