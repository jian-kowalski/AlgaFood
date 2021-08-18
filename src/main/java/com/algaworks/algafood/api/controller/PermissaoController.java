package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.disassembler.PermissaoInputDisassembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.model.input.PermissaoInput;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.service.PermissaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    private final PermissaoService permissaoService;

    private final PermissaoModelAssembler permissaoModelAssembler;

    private final PermissaoInputDisassembler permissaoInputDisassembler;

    public PermissaoController(PermissaoService permissaoService,
                               PermissaoModelAssembler permissaoModelAssembler, PermissaoInputDisassembler permissaoInputDisassembler) {
        this.permissaoService = permissaoService;
        this.permissaoModelAssembler = permissaoModelAssembler;
        this.permissaoInputDisassembler = permissaoInputDisassembler;
    }


    @GetMapping
    public List<PermissaoModel> listar() {
        return permissaoModelAssembler.toCollectionModel(permissaoService.buscarPermissoes());
    }

    @GetMapping("/{permissaoId}")
    public PermissaoModel buscar(@PathVariable Long permissaoId) {
        return permissaoModelAssembler.toModel(permissaoService.buscar(permissaoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoModel adicionar(@RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = permissaoInputDisassembler.toDomainObject(permissaoInput);
        return permissaoModelAssembler.toModel(permissaoService.adicionar(permissao));
    }

    @PutMapping("/{permissaoId}")
    public PermissaoModel alterar(@PathVariable Long permissaoId, @RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissaoAtual = permissaoService.buscar(permissaoId);
        permissaoInputDisassembler.copyToDomainObject(permissaoInput, permissaoAtual);
        return permissaoModelAssembler.toModel(permissaoService.adicionar(permissaoAtual));
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long permissaoId) {
        permissaoService.remover(permissaoId);
    }

}