package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.Model.PedidoModel;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping
    public List<PedidoModel> listarTodos() {
        return pedidoModelAssembler.toColletionModel(emissaoPedido.buscarTodos());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel listarPedido(@PathVariable Long pedidoId) {
        return pedidoModelAssembler.toModel(emissaoPedido.buscar(pedidoId));
    }
}
