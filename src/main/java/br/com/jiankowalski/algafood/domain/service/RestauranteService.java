package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeEmUsoException;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.exception.NegocioException;
import br.com.jiankowalski.algafood.domain.model.Cidade;
import br.com.jiankowalski.algafood.domain.model.Cozinha;
import br.com.jiankowalski.algafood.domain.model.Restaurante;
import br.com.jiankowalski.algafood.domain.repository.RestauranteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";

    private final RestauranteRepository restauranteRepository;

    private final CozinhaService cozinhaService;

    private final CidadeService cidadeService;

    private final FormaPagamentoService formaPagamentoService;
    private final UsuarioService cadastroUsuarioService;

    public RestauranteService(RestauranteRepository restauranteRepository,
                              CozinhaService cozinhaService,
                              CidadeService cidadeService,
                              FormaPagamentoService formaPagamentoService,
                              UsuarioService cadastroUsuarioService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
        this.formaPagamentoService = formaPagamentoService;
        this.cadastroUsuarioService = cadastroUsuarioService;
    }

    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        restaurante.setCozinha(retornarCozinhaPorId(cozinhaId));
        restaurante.getEndereco().setCidade(retornarCidadePorId(cidadeId));

        return restauranteRepository.save(restaurante);
    }

    private Cidade retornarCidadePorId(Long cidadeId) {
        try {
            return cidadeService.buscar(cidadeId);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
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
            throw new EntidadeNaoEncontradaException(String.format("Restaurante não encontrado para o código %d.", restauranteId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
        }
    }

    private Cozinha retornarCozinhaPorId(Long cozinhaId) {
        try {
            return cozinhaService.buscar(cozinhaId);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Restaurante não encontrado para o código %d.", restauranteId)));
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
        var formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void adicionarFormaPagamentoDoRestaurante(Long restauranteId, Long formaPagamentoId) {
        var restaurante = buscar(restauranteId);
        var formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
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

    public List<Restaurante> buscarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public List<Restaurante> procurarPorNomeECozinha(String nome, Long cozinhaId) {
        return null; //restauranteRepository.procurarPorNomeECozinha(nome, cozinhaId);
    }

    public List<Restaurante> procurarPorNomeTaxaIncialTaxaFinal(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteRepository.procurarPorNomeTaxaIncialTaxaFinal(nome, taxaFreteInicial, taxaFreteFinal);
    }

    public List<Restaurante> procurarPorFreteGratisENomeSemelhante(String nome) {
        return restauranteRepository.procurarPorFreteGratisENomeSemelhante(nome);
    }

    public List<Restaurante> procurarPorNomeCozinhaTaxa(String nome, Long cozinhaId, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteRepository.procurarPorNomeCozinhaTaxa(nome, cozinhaId, taxaFreteInicial, taxaFreteFinal);
    }

    public Restaurante buscarPrimeiroRestaurante() {
        return restauranteRepository.buscarPrimeiro().orElseThrow();
    }
}