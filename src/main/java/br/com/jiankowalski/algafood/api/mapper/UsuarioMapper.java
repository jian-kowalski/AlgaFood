package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.UsuarioModel;
import br.com.jiankowalski.algafood.api.model.input.UsuarioComSenhaInput;
import br.com.jiankowalski.algafood.api.model.input.UsuarioInput;
import br.com.jiankowalski.algafood.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper
public interface UsuarioMapper {
    List<UsuarioModel> toColletion(Set<Usuario> usuariosResponsaveis);

    List<UsuarioModel> toColletion(List<Usuario> listarTodos);

    UsuarioModel toModel(Usuario buscar);

    Usuario inputToDomain(UsuarioComSenhaInput usuarioComSenhaInput);

    void update(@MappingTarget Usuario usuarioAtual, UsuarioInput usuarioInput);
}
