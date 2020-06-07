package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamantoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

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
@RequestMapping("/formasPagamento")
public class FormaDePagamentoController {

    @Autowired
    private FormaPagamantoRepository formaPagamantoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @GetMapping
    public List<FormaPagamento> listar() {
        return formaPagamantoRepository.findAll();
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId) {
        Optional<FormaPagamento> forma = formaPagamantoRepository.findById(formaPagamentoId);
        if (forma.isPresent()) {
            return ResponseEntity.ok(forma.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
        return cadastroFormaPagamento.adicionar(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long formaPagamentoId,
            @RequestBody FormaPagamento formaPagamento) {
        Optional<FormaPagamento> formaPagamentoAtual = formaPagamantoRepository.findById(formaPagamentoId);
        if (formaPagamentoAtual.isPresent()) {
            BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");
            FormaPagamento formaPagementoSalva = cadastroFormaPagamento.adicionar(formaPagamentoAtual.get());
            return ResponseEntity.ok(formaPagementoSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> remover(@PathVariable Long formaPagamentoId) {
        try {
            cadastroFormaPagamento.remover(formaPagamentoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}