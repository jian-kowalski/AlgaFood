package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.PedidoMapper;
import br.com.jiankowalski.algafood.api.model.PedidoModel;
import br.com.jiankowalski.algafood.api.model.PedidoResumoModel;
import br.com.jiankowalski.algafood.api.model.input.PedidoInput;
import br.com.jiankowalski.algafood.domain.filter.PedidoFilter;
import br.com.jiankowalski.algafood.domain.model.Pedido;
import br.com.jiankowalski.algafood.domain.service.EmissaoPedidoService;
import br.com.jiankowalski.algafood.domain.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final EmissaoPedidoService emissaoPedido;
    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;


    public PedidoController(EmissaoPedidoService emissaoPedido, PedidoService pedidoService,
                            PedidoMapper pedidoMapper) {
        this.emissaoPedido = emissaoPedido;
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @GetMapping
    public Page<PedidoResumoModel> pesquisarPedidos(PedidoFilter pedidoFilter, Pageable pageable) {
        Page<Pedido> pedidosPage = pedidoService.filtarPedidosPaginados(pedidoFilter, pageable);
        List<PedidoResumoModel> pedidoResumoModels = pedidoMapper.toColletionModelResume(pedidosPage.getContent());
        return new PageImpl<>(pedidoResumoModels, pageable, pedidosPage.getTotalElements());
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel listarPedido(@PathVariable String codigoPedido) {
        return pedidoMapper.toModel(pedidoService.buscar(codigoPedido));
    }

    @PostMapping
    public PedidoModel adicionar(@RequestBody PedidoInput pedidoInput) {
        var pedido = pedidoMapper.inputToDomain(pedidoInput);
        return pedidoMapper.toModel(emissaoPedido.adicionar(pedido));
    }
}
