package com.algaworks.algafood.core.ModelMapper;

import com.algaworks.algafood.api.Model.input.ItemPedidoInput;
import com.algaworks.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
         ModelMapper modelMapper = new ModelMapper();
        // modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
        // .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        // var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        // enderecoToEnderecoModelTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
        //         (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
