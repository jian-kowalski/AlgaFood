package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamantoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

import org.springframework.beans.BeanUtils;
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
    public FormaPagamento buscar(@PathVariable Long formaPagamentoId) {
        return cadastroFormaPagamento.buscar(formaPagamentoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento adicionar(@RequestBody @Valid FormaPagamento formaPagamento) {
        return cadastroFormaPagamento.adicionar(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamento atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscar(formaPagamentoId);
        BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
        return cadastroFormaPagamento.adicionar(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamento.remover(formaPagamentoId);
    }
}