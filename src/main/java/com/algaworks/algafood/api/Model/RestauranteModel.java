package com.algaworks.algafood.api.Model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

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
