package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class ItemPedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private Integer quantidade;

  @Column(name = "preco_unitario",nullable = false)
  private BigDecimal precoUnitario;

  @Column(name = "preco_total",nullable = false)
  private BigDecimal precoTotal;

  private String observacao;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Produto produto;

  public void calcularPrecoTotal() {
    BigDecimal precoUnitario = this.getPrecoUnitario();
    Integer quantidade = this.getQuantidade();

    if (precoUnitario == null) {
      precoUnitario = BigDecimal.ZERO;
    }

    if (quantidade == null) {
      quantidade = 0;
    }

    this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
  }
}
