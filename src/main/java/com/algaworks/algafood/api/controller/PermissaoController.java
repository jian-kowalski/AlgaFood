package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.Model.PermissaoModel;
import com.algaworks.algafood.api.Model.input.PermissaoInput;
import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.disassembler.PermissaoInputDisassembler;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.CadastroPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissao;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private PermissaoInputDisassembler permissaoInputDisassembler;

    @GetMapping
    public List<PermissaoModel> listar() {
        return permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());
    }

    @GetMapping("/{permissaoId}")
    public PermissaoModel buscar(@PathVariable Long permissaoId) {
        return permissaoModelAssembler.toModel(cadastroPermissao.buscar(permissaoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoModel adicionar(@RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = permissaoInputDisassembler.toDomainObject(permissaoInput);
        return permissaoModelAssembler.toModel(cadastroPermissao.adicionar(permissao));
    }

    @PutMapping("/{permissaoId}")
    public PermissaoModel alterar(@PathVariable Long permissaoId, @RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissaoAtual = cadastroPermissao.buscar(permissaoId);
        permissaoInputDisassembler.copyToDomainObject(permissaoInput, permissaoAtual);
        return permissaoModelAssembler.toModel(cadastroPermissao.adicionar(permissaoAtual));
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long permissaoId) {
        cadastroPermissao.remover(permissaoId);
    }

}