package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.Model.EstadoModel;
import com.algaworks.algafood.api.Model.input.EstadoInput;
import com.algaworks.algafood.api.assembler.EstadoModelAssembeler;
import com.algaworks.algafood.api.disassembler.EstadoInputDisassembler;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

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
@RequestMapping("/estados")
public class EstadoController {

  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private CadastroEstadoService cadastroEstado;

  @Autowired
  private EstadoModelAssembeler estadoModelAssembeler;

  @Autowired
  private EstadoInputDisassembler estadoInputDisassembler;

  @GetMapping
  public List<EstadoModel> listar() {
    return estadoModelAssembeler.toCollectionModel(estadoRepository.findAll());
  }

  @GetMapping("/{estadoId}")
  public Estado buscar(@PathVariable Long estadoId) {
    return cadastroEstado.buscar(estadoId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
    Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
    return estadoModelAssembeler.toModel(cadastroEstado.adicionar(estado));
  }

  @PutMapping("/{estadoId}")
  public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
    Estado estadoAtual = cadastroEstado.buscar(estadoId);
    estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
    return estadoModelAssembeler.toModel(cadastroEstado.adicionar(estadoAtual));
  }

  @DeleteMapping("/{estadoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long estadoId) {
    cadastroEstado.remover(estadoId);
  }
}