package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.disassembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioModelAssembler usuarioModelAssembler;

    private final UsuarioInputDisassembler usuarioInputDisassembler;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioModelAssembler usuarioModelAssembler,
                             UsuarioInputDisassembler usuarioInputDisassembler,
                             UsuarioService usuarioService) {
        this.usuarioModelAssembler = usuarioModelAssembler;
        this.usuarioInputDisassembler = usuarioInputDisassembler;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toColletion(usuarioService.listarTodos());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscarUsuario(@PathVariable Long usuarioId) {
        return usuarioModelAssembler.toModel(usuarioService.buscar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
        var usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);

        return usuarioModelAssembler.toModel(usuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {
        var usuarioAtual = usuarioService.buscar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = usuarioService.salvar(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
