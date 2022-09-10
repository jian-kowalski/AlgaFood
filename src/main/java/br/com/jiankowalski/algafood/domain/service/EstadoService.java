package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeEmUsoException;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.model.Estado;
import br.com.jiankowalski.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstadoService {

    /**
     *
     */
    private static final String MSG_ESTADO_EM_USO = "Estado com o código %d não pode ser removida, pois está em uso";

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Transactional
    public Estado adicionar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void remover(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Estado não encontrado para o código %d", estadoId)) {
            };
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado buscar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Estado não encontrado para o código %d", estadoId)) {
        });
    }

    public List<Estado> buscarEstados() {
        return estadoRepository.findAll();
    }
}