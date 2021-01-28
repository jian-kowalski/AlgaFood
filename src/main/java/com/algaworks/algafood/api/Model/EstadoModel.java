package com.algaworks.algafood.api.Model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModel {

    private Long id;
    
    @NotBlank
    private String nome;
}
