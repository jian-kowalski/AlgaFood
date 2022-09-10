package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.FormaPagamentoMapper;
import br.com.jiankowalski.algafood.api.model.FormaPagamentoModel;
import br.com.jiankowalski.algafood.api.model.input.FormaPagamentoInput;
import br.com.jiankowalski.algafood.domain.model.FormaPagamento;
import br.com.jiankowalski.algafood.domain.service.FormaPagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formasPagamento")
public class FormaDePagamentoController {

    private final FormaPagamentoService formaPagamentoService;
    private final FormaPagamentoMapper formaPagamentoMapper;

    public FormaDePagamentoController(FormaPagamentoService formaPagamentoService, FormaPagamentoMapper formaPagamentoMapper) {
        this.formaPagamentoService = formaPagamentoService;
        this.formaPagamentoMapper = formaPagamentoMapper;
    }

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        return formaPagamentoMapper.toCollectionModel(formaPagamentoService.getFormasPagamento());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        return formaPagamentoMapper.toModel(formaPagamentoService.buscar(formaPagamentoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoMapper.inputToDomain(formaPagamentoInput);
        return formaPagamentoMapper.toModel(formaPagamentoService.adicionar(formaPagamento));
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscar(formaPagamentoId);
        formaPagamentoMapper.update(formaPagamentoAtual, formaPagamentoInput);
        return formaPagamentoMapper.toModel(formaPagamentoService.adicionar(formaPagamentoAtual));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.remover(formaPagamentoId);
    }
}