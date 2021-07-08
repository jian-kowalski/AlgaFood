package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler {

    public PedidoModel toModel(Pedido pedido) {
        var modelMappper = new ModelMapper();
        return modelMappper.map(pedido, PedidoModel.class);
    }

    public List<PedidoModel> toColletionModel(Collection<Pedido> pedidos) {
        return pedidos.stream().map(this::toModel).collect(Collectors.toList());
    }

}