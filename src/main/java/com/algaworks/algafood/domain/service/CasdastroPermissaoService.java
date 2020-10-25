package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CasdastroPermissaoService {

    private static final String MSG_PERMISSAO_EM_USO = "Permissão de código %d não pode ser removida, pois está em uso";
    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao adicionar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public void remover(Long permissaoId) {
        try {
            permissaoRepository.deleteById(permissaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new PermissaoNaoEncontradaException(permissaoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_PERMISSAO_EM_USO, permissaoId));
        }
    }

    public Permissao buscar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}