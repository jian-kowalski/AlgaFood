package br.com.jiankowalski.algafood.domain.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FluxoPedidoService {

    private final PedidoService pedidoService;

    public FluxoPedidoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Transactional
    public void confirmar(String codigoPedido) {
        var pedido = pedidoService.buscar(codigoPedido);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        var pedido = pedidoService.buscar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        var pedido = pedidoService.buscar(codigoPedido);
        pedido.cancelar();
    }


}
