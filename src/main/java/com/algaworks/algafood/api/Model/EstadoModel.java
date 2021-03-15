package com.algaworks.algafood.api.Model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoModel {

    private Long id;

    @NotBlank
    private String nome;
}
