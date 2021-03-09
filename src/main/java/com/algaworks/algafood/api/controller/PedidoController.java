package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.Model.PedidoModel;
import com.algaworks.algafood.api.Model.PedidoResumoModel;
import com.algaworks.algafood.api.Model.input.PedidoInput;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.disassembler.PedidoInputDisassembler;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoModel> listarTodos() {
        return pedidoResumoModelAssembler.toColletionModel(emissaoPedido.buscarTodos());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel listarPedido(@PathVariable Long pedidoId) {
        return pedidoModelAssembler.toModel(emissaoPedido.buscar(pedidoId));
    }

    @PostMapping
    public PedidoModel adicionar(@RequestBody PedidoInput pedidoInput){
        var pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        return pedidoModelAssembler.toModel(emissaoPedido.adicionar(pedido));
    }
}