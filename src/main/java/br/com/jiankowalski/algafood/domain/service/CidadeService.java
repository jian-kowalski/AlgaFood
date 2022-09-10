package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeEmUsoException;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.exception.NegocioException;
import br.com.jiankowalski.algafood.domain.model.Cidade;
import br.com.jiankowalski.algafood.domain.model.Estado;
import br.com.jiankowalski.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CidadeService {

    private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoService;

    public CidadeService(CidadeRepository cidadeRepository,
                         EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.estadoService = estadoService;
    }

    @Transactional
    public Cidade adicionar(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();
        Estado estado = retornarEstadoPorId(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);

    }

    private Estado retornarEstadoPorId(Long estadoId) {
        try {
            return estadoService.buscar(estadoId);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Transactional
    public void remover(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
            cidadeRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade não encontrada para o Código %d", cidadeId)) {
            };
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Cidade não encontrada para o Código %d", cidadeId)) {
        });
    }

    public List<Cidade> buscarCidades() {
        return cidadeRepository.findAll();
    }
}