package br.com.jiankowalski.algafood.api.model;

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
