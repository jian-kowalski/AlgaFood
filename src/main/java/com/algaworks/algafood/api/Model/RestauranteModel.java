package com.algaworks.algafood.api.Model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private Boolean ativo;

    private Boolean aberto;

    private CozinhaModel cozinha;

    private EnderecoModel endereco;

}
