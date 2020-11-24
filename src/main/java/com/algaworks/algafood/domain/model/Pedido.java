package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

  @Column(nullable = false)
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

  @ManyToOne
  @JoinColumn(nullable = false)
  private FormaPagamento formaPagamento;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurante restaurante;

  @ManyToOne
  @JoinColumn(name = "usuario_cliente_id", nullable = false)
  private Usuario usuario;

  @Column(nullable = false)
  private StatusPedido status;

  @Embedded
  private Endereco enderecoEntrega;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens = new ArrayList<>();


}
