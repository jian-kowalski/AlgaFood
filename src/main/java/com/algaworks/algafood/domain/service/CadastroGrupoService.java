package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO ="Grupo de código %d não pode ser removido, pois está em uso";
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    @Transactional
    public Grupo adicionar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo buscar(Long grupoid) {
        return grupoRepository.findById(grupoid).orElseThrow(() -> new GrupoNaoEncontradoException(grupoid));
    }

    @Transactional
	public void remover(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(grupoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
        }
	}

	@Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId){
        buscar(grupoId).removerPermissao(cadastroPermissaoService.buscar(permissaoId));
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId){
        buscar(grupoId).adicionarPermissao(cadastroPermissaoService.buscar(permissaoId));
    }


}
