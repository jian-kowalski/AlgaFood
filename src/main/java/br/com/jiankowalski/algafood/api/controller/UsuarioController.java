package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.UsuarioMapper;
import br.com.jiankowalski.algafood.api.model.UsuarioModel;
import br.com.jiankowalski.algafood.api.model.input.SenhaInput;
import br.com.jiankowalski.algafood.api.model.input.UsuarioComSenhaInput;
import br.com.jiankowalski.algafood.api.model.input.UsuarioInput;
import br.com.jiankowalski.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioMapper.toColletion(usuarioService.listarTodos());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscarUsuario(@PathVariable Long usuarioId) {
        return usuarioMapper.toModel(usuarioService.buscar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
        var usuario = usuarioMapper.inputToDomain(usuarioComSenhaInput);

        return usuarioMapper.toModel(usuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {
        var usuarioAtual = usuarioService.buscar(usuarioId);
        usuarioMapper.update(usuarioAtual, usuarioInput);
        usuarioAtual = usuarioService.salvar(usuarioAtual);

        return usuarioMapper.toModel(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
