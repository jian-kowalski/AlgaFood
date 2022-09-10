package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeEmUsoException;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.model.FormaPagamento;
import br.com.jiankowalski.algafood.domain.repository.FormaPagamantoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FormaPagamentoService {

    /**
     *
     */
    private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

    private final FormaPagamantoRepository formaPagamentoRepository;

    public FormaPagamentoService(FormaPagamantoRepository formaPagamentoRepository) {
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    @Transactional
    public FormaPagamento adicionar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void remover(Long formaPagamentoId) {
        try {
            formaPagamentoRepository.deleteById(formaPagamentoId);
            formaPagamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento não encontrada para o Código %d", formaPagamentoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaPagamentoId));
        }
    }

    public FormaPagamento buscar(Long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Forma de pagamento não encontrada para o Código %d", formaPagamentoId)));
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formaPagamentoRepository.findAll();
    }
}