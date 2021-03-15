package com.algaworks.algafood.api.Model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    private String nome;

}
