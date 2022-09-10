package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeEmUsoException;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.model.Permissao;
import br.com.jiankowalski.algafood.domain.repository.PermissaoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PermissaoService {

    private static final String MSG_PERMISSAO_EM_USO = "Permissão de código %d não pode ser removida, pois está em uso";

    private final PermissaoRepository permissaoRepository;

    public PermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    @Transactional
    public Permissao adicionar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    @Transactional
    public void remover(Long permissaoId) {
        try {
            permissaoRepository.deleteById(permissaoId);
            permissaoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Permissão não encontrada pra o código %d.", permissaoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_PERMISSAO_EM_USO, permissaoId));
        }
    }

    public Permissao buscar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Permissão não encontrada pra o código %d.", permissaoId)));
    }

    public List<Permissao> buscarPermissoes() {
        return permissaoRepository.findAll();
    }
}