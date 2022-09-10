package br.com.jiankowalski.algafood.api.model;


import br.com.jiankowalski.algafood.api.model.input.CidadeIdInput;
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
