package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

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

  @Column(name = "data_cancelamento",columnDefinition = "dateTime")
  private OffsetDateTime dataCancelamento;

  @Column(name = "data_entrega",columnDefinition = "dateTime")
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

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens = new ArrayList<>();


  public void calcularValorTotal() {
    this.subTotal = getItens()
            .stream()
            .map(ItemPedido::getPrecoTotal)
            .reduce(BigDecimal.ZERO,BigDecimal::add);

    this.valorTotal = this.subTotal.add(this.taxaFrete);
  }

  public void definirFrete() {
    setTaxaFrete(getRestaurante().getTaxaFrete());
  }

  public void atribuirPedidoAosItens() {
    getItens().forEach(item -> item.setPedido(this));
  }

}
