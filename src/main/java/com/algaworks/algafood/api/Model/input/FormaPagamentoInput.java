package com.algaworks.algafood.api.Model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class FormaPagamentoInput {

    @NotBlank
    private String descricao;
}
