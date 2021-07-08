package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.disassembler.PedidoInputDisassembler;
import com.algaworks.algafood.domain.service.CadastroPedidoService;
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
    private CadastroPedidoService cadastroPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoModel> listarTodos() {
        return pedidoResumoModelAssembler.toColletionModel(cadastroPedido.buscarTodos());
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel listarPedido(@PathVariable String codigoPedido) {
        return pedidoModelAssembler.toModel(cadastroPedido.buscar(codigoPedido));
    }

    @PostMapping
    public PedidoModel adicionar(@RequestBody PedidoInput pedidoInput) {
        var pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        return pedidoModelAssembler.toModel(emissaoPedido.adicionar(pedido));
    }
}
