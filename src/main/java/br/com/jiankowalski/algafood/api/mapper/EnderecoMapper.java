package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.EnderecoModel;
import br.com.jiankowalski.algafood.api.model.input.EnderecoInput;
import br.com.jiankowalski.algafood.domain.model.Endereco;
import org.mapstruct.Mapper;

@Mapper
public interface EnderecoMapper {
    Endereco enderecoInputToEndereco(EnderecoInput enderecoInput);

    EnderecoModel enderecoToEnderecoModel(Endereco endereco);
}
