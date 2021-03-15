package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuarioRepository.detach(usuario);

        validarEmailJaCadastrado(usuario);
        return usuarioRepository.save(usuario);
    }

    private void validarEmailJaCadastrado(Usuario usuario) {
        var usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            var msgEmailDuplicado = "Já exite um usuário cadastrado com o email %s";
            throw new NegocioException(String.format(msgEmailDuplicado, usuario.getEmail()));
        }
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        var usuario = buscar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        buscar(usuarioId).removerGrupo(cadastroGrupoService.buscar(grupoId));
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        buscar(usuarioId).adicionarGrupo(cadastroGrupoService.buscar(grupoId));
    }

}