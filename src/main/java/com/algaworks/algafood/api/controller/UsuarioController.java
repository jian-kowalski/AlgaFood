package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.Model.UsuarioModel;
import com.algaworks.algafood.api.Model.input.SenhaInput;
import com.algaworks.algafood.api.Model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.Model.input.UsuarioInput;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.disassembler.UsuarioInputDisassembler;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toColletion(cadastroUsuarioService.listarTodos());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscarUsuario(@PathVariable Long usuarioId) {
        return usuarioModelAssembler.toModel(cadastroUsuarioService.buscar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
        var usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
        
        return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
    }  
    
    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
            @RequestBody @Valid UsuarioInput usuarioInput) {
        var usuarioAtual = cadastroUsuarioService.buscar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);
        
        return usuarioModelAssembler.toModel(usuarioAtual);
    }
    
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    } 
}
