package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmissaoPedidoService {

    
    private final PedidoRepository pedidoRepository;

    
    private final RestauranteService cadastroRestaurante;


    private final UsuarioService cadastroUsuario;


    private final CidadeService cadastroCidade;


    private final FormaPagamentoService cadastroFormaPagamento;

    private final ProdutoService cadastroProduto;

    public EmissaoPedidoService(PedidoRepository pedidoRepository, RestauranteService cadastroRestaurante,
                                UsuarioService cadastroUsuario, CidadeService cadastroCidade,
                                FormaPagamentoService cadastroFormaPagamento, ProdutoService cadastroProduto) {
        this.pedidoRepository = pedidoRepository;
        this.cadastroRestaurante = cadastroRestaurante;
        this.cadastroUsuario = cadastroUsuario;
        this.cadastroCidade = cadastroCidade;
        this.cadastroFormaPagamento = cadastroFormaPagamento;
        this.cadastroProduto = cadastroProduto;
    }

    @Transactional
    public Pedido adicionar(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();
        return pedidoRepository.save(pedido);
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            var produto = cadastroProduto.buscar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    private void validarPedido(Pedido pedido) {
        var cidade = cadastroCidade.buscar(pedido.getEnderecoEntrega().getCidade().getId());
        var cliente = cadastroUsuario.buscar(1L);
        var restaurante = cadastroRestaurante.buscar(pedido.getRestaurante().getId());
        var formaPagamento = cadastroFormaPagamento.buscar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (!restaurante.aceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }


}
