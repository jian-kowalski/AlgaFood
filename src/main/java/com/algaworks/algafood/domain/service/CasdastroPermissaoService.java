package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CasdastroPermissaoService {
    
    @Autowired
    private PermissaoRepository permissaoRepository;

	public Permissao adicionar(Permissao permissao) {
		return permissaoRepository.save(permissao);
    }

	public void remover(Long permissaoId) {
        try {
            permissaoRepository.deleteById(permissaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                "Permissão não encontrada pra o código %d.", permissaoId));
        }
	}
}