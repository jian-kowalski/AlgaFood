package com.algaworks.algafood.api.Model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteIdInput {

    @NotNull
    private Long id;
}
