package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Pedido> filtarPedidosPaginados(PedidoFilter pedidoFilter, Pageable pageable) {
        return pedidoRepository.findAll(PedidoSpecs.usandoFiltro(pedidoFilter), pageable);
    }
}
