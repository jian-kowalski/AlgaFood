package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.Model.CozinhaModel;
import com.algaworks.algafood.api.Model.input.CozinhaInput;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembeler;
import com.algaworks.algafood.api.disassembler.CozinhaInputDisassembler;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/cozinhas")
public class CozinhaController {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinha;

  @Autowired
  private CozinhaModelAssembeler cozinhaModelAssembeler;

  @Autowired
  private CozinhaInputDisassembler cozinhaInputDisassembler;

  @GetMapping
  public List<CozinhaModel> listar() {
    return cozinhaModelAssembeler.toCollectionModel(cozinhaRepository.findAll());
  }

  @GetMapping("/{cozinhaId}")
  public CozinhaModel buscar(@PathVariable Long cozinhaId) {
    return cozinhaModelAssembeler.toModel(cadastroCozinha.buscar(cozinhaId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
    Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);

    return cozinhaModelAssembeler.toModel(cadastroCozinha.adicionar(cozinha));
  }

  @PutMapping("/{cozinhaId}")
  public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
    Cozinha cozinhaAtual = cadastroCozinha.buscar(cozinhaId);
    cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
    return cozinhaModelAssembeler.toModel(cadastroCozinha.adicionar(cozinhaAtual));
  }

  @DeleteMapping("/{cozinhaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long cozinhaId) {
    cadastroCozinha.remover(cozinhaId);
  }
}