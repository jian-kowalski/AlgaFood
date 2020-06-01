package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade adicionar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = retornarEstadoPorId(estadoId);
        tratarEstadoNaoEncontrado(estadoId, estado);
        cidade.setEstado(estado);
        return cidadeRepository.adicionar(cidade);
    }

    private Estado retornarEstadoPorId(Long estadoId) {
        return estadoRepository.porId(estadoId);
    }

    private void tratarEstadoNaoEncontrado(Long estadoId, Estado estado) {
        if (estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Estado não encontrado para o cód %d", estadoId));
        }
    }

    public void remover(Long cidadeId) {
        Cidade cidade = cidadeRepository.porId(cidadeId);
        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade não encontrada para o Código %d", cidadeId));
        }
        cidadeRepository.remover(cidade);
    }

}