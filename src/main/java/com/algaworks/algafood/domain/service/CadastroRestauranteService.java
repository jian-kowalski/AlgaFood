package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = retornarCozinhaPorId(cozinhaId);
        trataCozinhaNaoExistente(cozinhaId, cozinha);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante alterar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = retornarCozinhaPorId(cozinhaId);
        trataCozinhaNaoExistente(cozinhaId, cozinha);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public void remover(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante não encontrado pra o código %d.", id));
        }
    }

    private void trataCozinhaNaoExistente(Long cozinhaId, Cozinha cozinha) {
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha não encontrada para o cód %d", cozinhaId));
        }
    }

    private Cozinha retornarCozinhaPorId(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Cozinha não encontrada para o cód %d", cozinhaId)));
    }

}