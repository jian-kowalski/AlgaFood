package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.ProdutoMapper;
import br.com.jiankowalski.algafood.api.model.ProdutoModel;
import br.com.jiankowalski.algafood.api.model.input.ProdutoInput;
import br.com.jiankowalski.algafood.domain.model.Produto;
import br.com.jiankowalski.algafood.domain.model.Restaurante;
import br.com.jiankowalski.algafood.domain.service.ProdutoService;
import br.com.jiankowalski.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    private final ProdutoService produtoService;
    private final RestauranteService cadastroRestaurante;
    private final ProdutoMapper produtoMapper;


    public RestauranteProdutoController(ProdutoService produtoService,
                                        RestauranteService cadastroRestaurante,
                                        ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.cadastroRestaurante = cadastroRestaurante;
        this.produtoMapper = produtoMapper;
    }

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId,
                                     @RequestParam(required = false) boolean inativos) {
        Restaurante restaurante = cadastroRestaurante.buscar(restauranteId);
        return produtoMapper.toColletion(produtoService.buscarPorRestaurante(inativos, restaurante));
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscar(restauranteId, produtoId);

        return produtoMapper.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = cadastroRestaurante.buscar(restauranteId);

        Produto produto = produtoMapper.inputToDomain(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);

        return produtoMapper.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscar(restauranteId, produtoId);

        produtoMapper.update(produtoAtual, produtoInput);

        return produtoMapper.toModel(produtoService.salvar(produtoAtual));
    }
}
