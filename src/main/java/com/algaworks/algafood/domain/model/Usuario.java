package com.algaworks.algafood.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @JsonIgnore
  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "dateTime")
  private OffsetDateTime dataCadastro;

  @ManyToMany
  @JoinTable(name = "usuario_grupo", joinColumns =
             @JoinColumn(name = "grupo_id"), 
             inverseJoinColumns = @JoinColumn(name = "usuario_id"))
  private List<Grupo> grupos = new ArrayList<>();

  public boolean senhaNaoCoincideCom(String senhaAtual) {
    return !senhaCoincideCom(senhaAtual);
  }

  public boolean senhaCoincideCom(String senha) {
    return getSenha().equals(senha);
}

}
