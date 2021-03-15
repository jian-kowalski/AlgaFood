package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> procurarPorNomeTaxaIncialTaxaFinal(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> procurarPorNomeCozinhaTaxa(String nome, Long cozinhaId, BigDecimal taxaFreteInicial,
                                                 BigDecimal taxaFreteFinal);

    List<Restaurante> procurarPorFreteGratisENomeSemelhante(String nome);

}
