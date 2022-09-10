package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.RestauranteMapper;
import br.com.jiankowalski.algafood.api.model.RestauranteModel;
import br.com.jiankowalski.algafood.api.model.input.RestauranteInput;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.exception.NegocioException;
import br.com.jiankowalski.algafood.domain.model.Restaurante;
import br.com.jiankowalski.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    private final RestauranteMapper restauranteMapper;

    public RestauranteController(RestauranteService cadastroRestaurante, RestauranteMapper restauranteMapper) {
        this.restauranteService = cadastroRestaurante;
        this.restauranteMapper = restauranteMapper;
    }

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteMapper.toCollectionModel(restauranteService.buscarRestaurantes());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        return restauranteMapper.toModel(restauranteService.buscar(restauranteId));
    }

    @GetMapping("/por-nome")
    public List<RestauranteModel> restaurantePorNome(Long cozinhaId, String nome) {
        return restauranteMapper
                .toCollectionModel(restauranteService.procurarPorNomeECozinha(nome, cozinhaId));
    }

    @GetMapping("/por-nome-e-frente")
    public List<RestauranteModel> restaurantePorNomeEFrente(String nome, BigDecimal taxaFreteInicial,
                                                            BigDecimal taxaFreteFinal) {
        return restauranteMapper.toCollectionModel(
                restauranteService.procurarPorNomeTaxaIncialTaxaFinal(nome, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/com-frete-gratis")
    public List<RestauranteModel> comFreteGratis(String nome) {
        return restauranteMapper
                .toCollectionModel(restauranteService.procurarPorFreteGratisENomeSemelhante(nome));
    }

    @GetMapping("/por-nome-cozinha-taxa")
    public List<RestauranteModel> procurarPorNomeCozinhaTaxa(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
                                                             BigDecimal taxaFreteFinal) {
        return restauranteMapper.toCollectionModel(
                restauranteService.procurarPorNomeCozinhaTaxa(nome, cozinhaId, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/buscar-primeiro")
    public RestauranteModel buscarPrimeiro(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
                                           BigDecimal taxaFreteFinal) {
        return restauranteMapper.toModel(restauranteService.buscarPrimeiroRestaurante());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restaurante = restauranteMapper.inputToDomain(restauranteInput);
        return restauranteMapper.toModel(restauranteService.adicionar(restaurante));
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel alterar(@PathVariable Long restauranteId,
                                    @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restauranteAtual = restauranteService.buscar(restauranteId);
        restauranteMapper.update(restauranteAtual, restauranteInput);
        return restauranteMapper.toModel(restauranteService.adicionar(restauranteAtual));
    }

    @PutMapping("/ativacaoes")
    public void ativarMultiplos(@RequestBody List<Long> resturantesIds) {
        try {
            restauranteService.ativarRestarantes(resturantesIds);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/ativacaoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> resturantesIds) {
        try {
            restauranteService.inativarRestarantes(resturantesIds);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {
        restauranteService.remover(restauranteId);
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    public void inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrirRestaurante(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    public void fecharRestaurante(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
    }

}