package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private  CadastroPedidoService cadastroPedido;

    @Transactional
    public void confirmar(String codigoPedido) {
        var pedido = cadastroPedido.buscar(codigoPedido);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        var pedido = cadastroPedido.buscar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        var pedido = cadastroPedido.buscar(codigoPedido);
        pedido.cancelar();
    }


}
