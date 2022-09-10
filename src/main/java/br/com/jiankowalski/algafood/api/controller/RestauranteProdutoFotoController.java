package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.FotoProdutoMapper;
import br.com.jiankowalski.algafood.api.model.FotoProdutoModel;
import br.com.jiankowalski.algafood.api.model.input.FotoProdutoInput;
import br.com.jiankowalski.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.jiankowalski.algafood.domain.model.FotoProduto;
import br.com.jiankowalski.algafood.domain.model.Produto;
import br.com.jiankowalski.algafood.domain.service.CatalogoFotoProdutoService;
import br.com.jiankowalski.algafood.domain.service.FotoStorageService;
import br.com.jiankowalski.algafood.domain.service.ProdutoService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    private final ProdutoService produtoService;
    private final CatalogoFotoProdutoService catalogoFotoProduto;
    private final FotoStorageService fotoStorage;
    private final FotoProdutoMapper fotoProdutoMapper;

    public RestauranteProdutoFotoController(ProdutoService produtoService, CatalogoFotoProdutoService catalogoFotoProduto,
                                            FotoStorageService fotoStorage, FotoProdutoMapper fotoProdutoMapper) {
        this.produtoService = produtoService;
        this.catalogoFotoProduto = catalogoFotoProduto;
        this.fotoStorage = fotoStorage;
        this.fotoProdutoMapper = fotoProdutoMapper;
    }


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId,
                                          @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
        Produto produto = produtoService.buscar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());

        return fotoProdutoMapper.toModel(fotoSalva);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId,
                        @PathVariable Long produtoId) {
        catalogoFotoProduto.excluir(restauranteId, produtoId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

        return fotoProdutoMapper.toModel(fotoProduto);
    }

    @GetMapping
    public ResponseEntity<Object> servir(@PathVariable Long restauranteId,
                                         @PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
            var foto = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
            if (foto.temUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, foto.getUrl())
                        .build();

            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(foto.getInputStream()));
            }
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
                                                   List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }

}
