package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.FormaPagamentoMapper;
import br.com.jiankowalski.algafood.api.model.FormaPagamentoModel;
import br.com.jiankowalski.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    private final RestauranteService restauranteService;
    private final FormaPagamentoMapper formaPagamentoMapper;

    public RestauranteFormaPagamentoController(RestauranteService cadastroRestaurante,
                                               FormaPagamentoMapper formaPagamentoMapper) {
        this.restauranteService = cadastroRestaurante;
        this.formaPagamentoMapper = formaPagamentoMapper;
    }

    @GetMapping
    public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscar(restauranteId);
        return formaPagamentoMapper.toCollectionModel(restaurante.getFormasPagamento());
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
