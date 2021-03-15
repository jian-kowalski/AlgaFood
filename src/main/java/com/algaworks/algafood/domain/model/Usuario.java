package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Grupo> grupos = new HashSet<>();

    public boolean senhaNaoCoincideCom(String senhaAtual) {
        return !senhaCoincideCom(senhaAtual);
    }

    public boolean senhaCoincideCom(String senha) {
        return getSenha().equals(senha);
    }

    public boolean removerGrupo(Grupo grupo) {
        return grupos.remove(grupo);
    }

    public boolean adicionarGrupo(Grupo grupo) {
        return grupos.add(grupo);
    }

}
