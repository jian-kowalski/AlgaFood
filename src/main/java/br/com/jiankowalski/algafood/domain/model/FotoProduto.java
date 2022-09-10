package br.com.jiankowalski.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "foto_produto")
public class FotoProduto {

    @Id
    @Column(name = "produto_id",nullable = false)
    private  Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private  Produto produto;

    @Column(length = 150)
    private String descricao;

    @Column(nullable = false, length = 150)
    private String nomeArquivo;

    @Column(nullable = false, length = 80)
    private String contentType;

    @Column(nullable = false)
    private Long tamanho;


}
