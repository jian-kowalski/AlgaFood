package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.model.Produto;
import br.com.jiankowalski.algafood.domain.model.Restaurante;
import br.com.jiankowalski.algafood.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d", produtoId,
                        restauranteId)));
    }

    public List<Produto> buscarPorRestaurante(boolean inativos, Restaurante restaurante) {
        if (inativos)
            return produtoRepository.findByRestaurante(restaurante);
        return produtoRepository.findAtivosByRestaurante(restaurante);
    }

    public void atualizarFoto() {
    }
}
