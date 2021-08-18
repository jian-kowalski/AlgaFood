package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIntegracaoItTests {

    @Autowired
    private CozinhaService cadastroCozinha;

    @Test
    void testarCadastroCozinhaComSucesso() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");
        novaCozinha = cadastroCozinha.adicionar(novaCozinha);
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    void testarCadastroCozinhaSemDescricao() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("");
        assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.adicionar(novaCozinha));
    }

    @Test
    void testarCadastroCozinhaComDecricaoNula() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);
        assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.adicionar(novaCozinha));
    }

    @Test
    void testarExclusaoDeCozinhaEmUso() {
        assertThrows(EntidadeEmUsoException.class, () -> cadastroCozinha.remover(1L));
    }

    @Test
    void testarExclusaoDeCozinhaInexitente() {
        assertThrows(EntidadeNaoEncontradaException.class, () -> cadastroCozinha.remover(155L));
    }
}