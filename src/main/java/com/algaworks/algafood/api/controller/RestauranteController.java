package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.Model.RestauranteModel;
import com.algaworks.algafood.api.Model.input.RestauranteInput;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembeler;
import com.algaworks.algafood.api.disassembler.RestauranteInputDisassembler;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

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
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembeler restauranteModelAssembeler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembeler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        return restauranteModelAssembeler.toModel(cadastroRestaurante.buscar(restauranteId));
    }

    @GetMapping("/por-nome")
    public List<RestauranteModel> restaurantePorNome(Long cozinhaId, String nome) {
        return restauranteModelAssembeler
                .toCollectionModel(restauranteRepository.procurarPorNomeECozinha(nome, cozinhaId));
    }

    @GetMapping("/por-nome-e-frente")
    public List<RestauranteModel> restaurantePorNomeEFrente(String nome, BigDecimal taxaFreteInicial,
            BigDecimal taxaFreteFinal) {
        return restauranteModelAssembeler.toCollectionModel(
                restauranteRepository.procurarPorNomeTaxaIncialTaxaFinal(nome, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/com-frete-gratis")
    public List<RestauranteModel> ComFreteGratis(String nome) {
        return restauranteModelAssembeler
                .toCollectionModel(restauranteRepository.procurarPorFreteGratisENomeSemelhante(nome));
    }

    @GetMapping("/por-nome-cozinha-taxa")
    public List<RestauranteModel> procurarPorNomeCozinhaTaxa(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
            BigDecimal taxaFreteFinal) {
        return restauranteModelAssembeler.toCollectionModel(
                restauranteRepository.procurarPorNomeCozinhaTaxa(nome, cozinhaId, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/buscar-primeiro")
    public RestauranteModel buscarPrimeiro(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
            BigDecimal taxaFreteFinal) {
        return restauranteModelAssembeler.toModel(restauranteRepository.buscarPrimeiro().orElseThrow());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembeler.toModel(cadastroRestaurante.adicionar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel alterar(@PathVariable Long restauranteId,
            @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restauranteAtual = cadastroRestaurante.buscar(restauranteId);
        Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
                "produtos");
        try {
            return restauranteModelAssembeler.toModel(cadastroRestaurante.adicionar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {
        cadastroRestaurante.remover(restauranteId);
    }

}