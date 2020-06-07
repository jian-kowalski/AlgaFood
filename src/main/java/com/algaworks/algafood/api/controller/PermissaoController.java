package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.CasdastroPermissaoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CasdastroPermissaoService cadastroPermissao;

    @GetMapping
    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    @GetMapping("/{permissaoId}")
    public ResponseEntity<Permissao> buscar(@PathVariable Long permissaoId) {
        Optional<Permissao> permissao = permissaoRepository.findById(permissaoId);
        if (permissao.isPresent()) {
            return ResponseEntity.ok(permissao.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permissao adicionar(@RequestBody Permissao permissao) {
        return cadastroPermissao.adicionar(permissao);
    }

    @PutMapping("/{permissaoId}")
    public ResponseEntity<?> alterar(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {
        Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);
        if (permissaoAtual.isPresent()) {
            BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");
            cadastroPermissao.adicionar(permissaoAtual.get());
            return ResponseEntity.ok(permissaoAtual.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<Permissao> remover(@PathVariable Long permissaoId) {
        try {
            cadastroPermissao.remover(permissaoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}