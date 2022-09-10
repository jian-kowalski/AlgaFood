package br.com.jiankowalski.algafood.api.mapper;

import br.com.jiankowalski.algafood.api.model.ItemPedidoModel;
import br.com.jiankowalski.algafood.api.model.input.ItemPedidoInput;
import br.com.jiankowalski.algafood.domain.model.ItemPedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ItemPedidoMapper {

    List<ItemPedidoModel> itemPedidoListToItemPedidoModelList(List<ItemPedido> list);

    List<ItemPedido> itemPedidoInputListToItemPedidoList(List<ItemPedidoInput> list);
}
