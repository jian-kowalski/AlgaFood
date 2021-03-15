package com.algaworks.algafood.api.Model;


import com.algaworks.algafood.api.Model.input.CidadeIdInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairo;

    private CidadeIdInput cidade;

}
