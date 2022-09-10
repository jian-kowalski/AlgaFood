package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.PermissaoMapper;
import br.com.jiankowalski.algafood.api.model.PermissaoModel;
import br.com.jiankowalski.algafood.api.model.input.PermissaoInput;
import br.com.jiankowalski.algafood.domain.model.Permissao;
import br.com.jiankowalski.algafood.domain.service.PermissaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    private final PermissaoService permissaoService;

    private final PermissaoMapper permissaoMapper;

    public PermissaoController(PermissaoService permissaoService, PermissaoMapper permissaoMapper) {
        this.permissaoService = permissaoService;
        this.permissaoMapper = permissaoMapper;
    }


    @GetMapping
    public List<PermissaoModel> listar() {
        return permissaoMapper.toCollectionModel(permissaoService.buscarPermissoes());
    }

    @GetMapping("/{permissaoId}")
    public PermissaoModel buscar(@PathVariable Long permissaoId) {
        return permissaoMapper.toModel(permissaoService.buscar(permissaoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoModel adicionar(@RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissao = permissaoMapper.inputToDomain(permissaoInput);
        return permissaoMapper.toModel(permissaoService.adicionar(permissao));
    }

    @PutMapping("/{permissaoId}")
    public PermissaoModel alterar(@PathVariable Long permissaoId, @RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao permissaoAtual = permissaoService.buscar(permissaoId);
        permissaoMapper.update(permissaoAtual, permissaoInput);
        return permissaoMapper.toModel(permissaoService.adicionar(permissaoAtual));
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long permissaoId) {
        permissaoService.remover(permissaoId);
    }

}