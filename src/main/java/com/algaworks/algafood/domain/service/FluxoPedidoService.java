package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;;

@Service
public class FluxoPedidoService {

    @Autowired
    private  CadastroPedidoService cadastroPedido;

    @Transactional
    public void confirmar(Long pedidoId) {
        var pedido = cadastroPedido.buscar(pedidoId);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(Long pedidoId) {
        var pedido = cadastroPedido.buscar(pedidoId);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        var pedido = cadastroPedido.buscar(pedidoId);
        pedido.cancelar();
    }


}
