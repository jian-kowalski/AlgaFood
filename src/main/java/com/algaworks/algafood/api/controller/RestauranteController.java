package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.disassembler.RestauranteInputDisassembler;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        return restauranteModelAssembler.toModel(cadastroRestaurante.buscar(restauranteId));
    }

    @GetMapping("/por-nome")
    public List<RestauranteModel> restaurantePorNome(Long cozinhaId, String nome) {
        return restauranteModelAssembler
                .toCollectionModel(restauranteRepository.procurarPorNomeECozinha(nome, cozinhaId));
    }

    @GetMapping("/por-nome-e-frente")
    public List<RestauranteModel> restaurantePorNomeEFrente(String nome, BigDecimal taxaFreteInicial,
                                                            BigDecimal taxaFreteFinal) {
        return restauranteModelAssembler.toCollectionModel(
                restauranteRepository.procurarPorNomeTaxaIncialTaxaFinal(nome, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/com-frete-gratis")
    public List<RestauranteModel> comFreteGratis(String nome) {
        return restauranteModelAssembler
                .toCollectionModel(restauranteRepository.procurarPorFreteGratisENomeSemelhante(nome));
    }

    @GetMapping("/por-nome-cozinha-taxa")
    public List<RestauranteModel> procurarPorNomeCozinhaTaxa(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
                                                             BigDecimal taxaFreteFinal) {
        return restauranteModelAssembler.toCollectionModel(
                restauranteRepository.procurarPorNomeCozinhaTaxa(nome, cozinhaId, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/buscar-primeiro")
    public RestauranteModel buscarPrimeiro(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
                                           BigDecimal taxaFreteFinal) {
        return restauranteModelAssembler.toModel(restauranteRepository.buscarPrimeiro().orElseThrow());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestaurante.adicionar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel alterar(@PathVariable Long restauranteId,
                                    @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restauranteAtual = cadastroRestaurante.buscar(restauranteId);
        restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
        try {
            return restauranteModelAssembler.toModel(cadastroRestaurante.adicionar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/ativacaoes")
    public void ativarMultiplos(@RequestBody List<Long> resturantesIds) {
        try {
            cadastroRestaurante.ativarRestarantes(resturantesIds);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/ativacaoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> resturantesIds) {
        try {
            cadastroRestaurante.inativarRestarantes(resturantesIds);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {
        cadastroRestaurante.remover(restauranteId);
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    public void inativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.inativar(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrirRestaurante(@PathVariable Long restauranteId) {
        cadastroRestaurante.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    public void fecharRestaurante(@PathVariable Long restauranteId) {
        cadastroRestaurante.fechar(restauranteId);
    }

}