package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.disassembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.domain.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final EmissaoPedidoService emissaoPedido;
    private final PedidoService pedidoService;
    private final PedidoModelAssembler pedidoModelAssembler;
    private final PedidoResumoModelAssembler pedidoResumoModelAssembler;
    private final PedidoInputDisassembler pedidoInputDisassembler;

    public PedidoController(EmissaoPedidoService emissaoPedido, PedidoService pedidoService, PedidoModelAssembler pedidoModelAssembler,
                            PedidoResumoModelAssembler pedidoResumoModelAssembler, PedidoInputDisassembler pedidoInputDisassembler) {
        this.emissaoPedido = emissaoPedido;
        this.pedidoService = pedidoService;
        this.pedidoModelAssembler = pedidoModelAssembler;
        this.pedidoResumoModelAssembler = pedidoResumoModelAssembler;
        this.pedidoInputDisassembler = pedidoInputDisassembler;
    }

    @GetMapping
    public List<PedidoResumoModel> listarTodos() {
        return pedidoResumoModelAssembler.toColletionModel(pedidoService.buscarTodos());
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel listarPedido(@PathVariable String codigoPedido) {
        return pedidoModelAssembler.toModel(pedidoService.buscar(codigoPedido));
    }

    @PostMapping
    public PedidoModel adicionar(@RequestBody PedidoInput pedidoInput) {
        var pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        return pedidoModelAssembler.toModel(emissaoPedido.adicionar(pedido));
    }
}
