package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeEmUsoException;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.model.Grupo;
import br.com.jiankowalski.algafood.domain.repository.GrupoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GrupoService {

    private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

    private final GrupoRepository grupoRepository;
    private final PermissaoService permissaoService;

    public GrupoService(GrupoRepository grupoRepository, PermissaoService permissaoService) {
        this.grupoRepository = grupoRepository;
        this.permissaoService = permissaoService;
    }

    @Transactional
    public Grupo adicionar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo buscar(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Grupo não encontrado para o código %d.", grupoId)));
    }

    @Transactional
    public void remover(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Grupo não encontrado para o código %d.", grupoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
        }
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        buscar(grupoId).removerPermissao(permissaoService.buscar(permissaoId));
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        buscar(grupoId).adicionarPermissao(permissaoService.buscar(permissaoId));
    }


    public List<Grupo> buscarGrupos() {
        return grupoRepository.findAll();
    }
}
