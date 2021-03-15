package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIntegracaoItTests {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void testarCadastroCozinhaComSucesso() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");
        novaCozinha = cadastroCozinha.adicionar(novaCozinha);
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void testarCadastroCozinhaSemDescricao() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("");
        assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.adicionar(novaCozinha));
    }

    @Test
    public void testarCadastroCozinhaComDecricaoNula() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);
        assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.adicionar(novaCozinha));
    }

    @Test
    public void testarExclusaoDeCozinhaEmUso() {
        assertThrows(EntidadeEmUsoException.class, () -> cadastroCozinha.remover(1L));
    }

    @Test
    public void testarExclusaoDeCozinhaInexitente() {
        assertThrows(CozinhaNaoEncontradaException.class, () -> cadastroCozinha.remover(155L));
    }
}