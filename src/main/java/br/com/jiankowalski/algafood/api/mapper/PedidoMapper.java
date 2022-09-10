package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.PedidoModel;
import br.com.jiankowalski.algafood.api.model.PedidoResumoModel;
import br.com.jiankowalski.algafood.api.model.input.PedidoInput;
import br.com.jiankowalski.algafood.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {EnderecoMapper.class, RestauranteMapper.class, FormaPagamentoMapper.class, UsuarioMapper.class, ItemPedidoMapper.class})
public interface PedidoMapper {
    PedidoModel toModel(Pedido pedido);

    List<PedidoResumoModel> toColletionModelResume(List<Pedido> content);

    @Mapping(target = "formaPagamento", source = "formaPagamento")
    @Mapping(target = "restaurante", source = "restaurante")
    @Mapping(target = "enderecoEntrega", source = "enderecoEntrega")
    @Mapping(target = "itens", source = "itens")
    Pedido inputToDomain(PedidoInput pedidoInput);
}
