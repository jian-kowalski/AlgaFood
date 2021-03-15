package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.domain.exception.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String codigo;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subTotal;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, columnDefinition = "dateTime")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_confirmacao", columnDefinition = "dateTime")
    private OffsetDateTime dataConfirmacao;

    @Column(name = "data_cancelamento", columnDefinition = "dateTime")
    private OffsetDateTime dataCancelamento;

    @Column(name = "data_entrega", columnDefinition = "dateTime")
    private OffsetDateTime dataEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @Embedded
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);
        this.subTotal = getItens()
                .stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subTotal.add(this.taxaFrete);
    }

    public void definirFrete() {
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }

    public void atribuirPedidoAosItens() {
        getItens().forEach(item -> item.setPedido(this));
    }

    public void confirmar() {
        this.setStatus(StatusPedido.CONFIRMADO);
        this.setDataConfirmacao(OffsetDateTime.now());
    }

    public void entregar() {
        this.setStatus(StatusPedido.ENTREGUE);
        this.setDataEntrega(OffsetDateTime.now());
    }

    public void cancelar() {
        this.setStatus(StatusPedido.CANCELADO);
        this.setDataCancelamento(OffsetDateTime.now());
    }

    private void setStatus(StatusPedido novoStatus) {
        if (!getStatus().podeAlterarPara(novoStatus)) {
            throw new NegocioException(
                    String.format("Status do pedido não pode ser alterado de %s para %s",
                            getStatus().getDescricao(), novoStatus.getDescricao()));
        }
        this.status = novoStatus;
    }

}
