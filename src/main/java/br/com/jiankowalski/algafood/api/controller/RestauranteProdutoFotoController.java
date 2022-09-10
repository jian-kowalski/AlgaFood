package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.model.input.FotoProdutoInput;
import br.com.jiankowalski.algafood.domain.service.ProdutoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    private final ProdutoService produtoService;

    public RestauranteProdutoFotoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                       @Valid FotoProdutoInput fotoProdutoInput) {

        produtoService.atualizarFoto();
        var nomeArq = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
        var arquivoFoto = Path.of("/home/jian/Imagens/catalogo/", nomeArq);

        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
