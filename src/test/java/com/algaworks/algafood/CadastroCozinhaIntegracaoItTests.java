package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.algaworks.algafood.util.DatabaseCleaner;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Test
    void testarCadastroCozinhaComSucesso() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");
        novaCozinha = cadastroCozinha.adicionar(novaCozinha);
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @BeforeEach
    void setUp(){
        System.out.println("entrou");
        databaseCleaner.clearTables();
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
    void testarExclusaoDeCozinhaInexitente() {
        assertThrows(EntidadeNaoEncontradaException.class, () -> cadastroCozinha.remover(155L));
    }
}