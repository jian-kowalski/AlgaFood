package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscar(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe um pedido com código %s", codigoPedido)));
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

}
