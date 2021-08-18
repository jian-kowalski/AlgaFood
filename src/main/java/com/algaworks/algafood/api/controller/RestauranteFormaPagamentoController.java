package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    private final RestauranteService restauranteService;
    private final FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    public RestauranteFormaPagamentoController(RestauranteService cadastroRestaurante,
                                               FormaPagamentoModelAssembler formaPagamentoModelAssembler) {
        this.restauranteService = cadastroRestaurante;
        this.formaPagamentoModelAssembler = formaPagamentoModelAssembler;
    }

    @GetMapping
    public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscar(restauranteId);
        return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento());
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFormaPagamentoDoRestaurante(@PathVariable Long restauranteId,
                                                   @PathVariable Long formaPagamentoId) {
        restauranteService.removerFormaPagamentoDoRestaurante(restauranteId, formaPagamentoId);
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarFormaPagamentoDoRestaurante(@PathVariable Long restauranteId,
                                                     @PathVariable Long formaPagamentoId) {
        restauranteService.adicionarFormaPagamentoDoRestaurante(restauranteId, formaPagamentoId);
    }

}
