package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.model.Pedido;
import br.com.jiankowalski.algafood.domain.repository.PedidoRepository;
import br.com.jiankowalski.algafood.domain.filter.PedidoFilter;
import br.com.jiankowalski.algafood.infrastructure.repository.spec.PedidoSpecs;
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
