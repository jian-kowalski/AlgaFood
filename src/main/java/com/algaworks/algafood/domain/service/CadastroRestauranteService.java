package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = retornarCozinhaPorId(cozinhaId);
        Cidade cidade = cadastroCidadeService.buscar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante alterar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = retornarCozinhaPorId(cozinhaId);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void remover(Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
        }
    }

    private Cozinha retornarCozinhaPorId(Long cozinhaId) {
        return cadastroCozinhaService.buscar(cozinhaId);
    }

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
    public void ativar(Long restauranteId) {
        buscar(restauranteId).ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        buscar(restauranteId).inativar();
    }

    @Transactional
    public void abrir(Long restauranteId) {
        buscar(restauranteId).abrir();
    }

    @Transactional
    public void ativarRestarantes(List<Long> restauranteIds) {
        restauranteIds.forEach(this::ativar);
    }

    @Transactional
    public void inativarRestarantes(List<Long> restauranteIds) {
        restauranteIds.forEach(this::inativar);
    }

    @Transactional
    public void fechar(Long restauranteId) {
        buscar(restauranteId).fechar();
    }

    @Transactional
    public void removerFormaPagamentoDoRestaurante(Long restauranteId, Long formaPagamentoId) {
        var restaurante = buscar(restauranteId);
        var formaPagamento = cadastroFormaPagamentoService.buscar(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void adicionarFormaPagamentoDoRestaurante(Long restauranteId, Long formaPagamentoId) {
        var restaurante = buscar(restauranteId);
        var formaPagamento = cadastroFormaPagamentoService.buscar(formaPagamentoId);
        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void removerResponsavelDoRestaurante(Long restauranteId, Long usuarioId) {
        buscar(restauranteId).removerResponsavel(cadastroUsuarioService.buscar(usuarioId));
    }

    @Transactional
    public void adicionarResponsavelDoRestaurante(Long restauranteId, Long usuarioId) {
        buscar(restauranteId).adicionarResponsavel(cadastroUsuarioService.buscar(usuarioId));
    }

}