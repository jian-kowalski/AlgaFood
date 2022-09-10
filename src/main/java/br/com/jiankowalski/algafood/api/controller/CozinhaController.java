package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.CozinhaMapper;
import br.com.jiankowalski.algafood.api.model.CozinhaModel;
import br.com.jiankowalski.algafood.api.model.input.CozinhaInput;
import br.com.jiankowalski.algafood.domain.model.Cozinha;
import br.com.jiankowalski.algafood.domain.service.CozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService cozinhaService;

    private final CozinhaMapper cozinhaMapper;


    public CozinhaController(CozinhaService cozinhaService, CozinhaMapper cozinhaMapper) {
        this.cozinhaService = cozinhaService;
        this.cozinhaMapper = cozinhaMapper;
    }

    @GetMapping
    public List<CozinhaModel> listar() {
        return cozinhaMapper.toCollectionModel(cozinhaService.buscarCozinhas());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return cozinhaMapper.toModel(cozinhaService.buscar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaMapper.inputToDomain(cozinhaInput);

        return cozinhaMapper.toModel(cozinhaService.adicionar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaService.buscar(cozinhaId);
        cozinhaMapper.update(cozinha, cozinhaInput);
        return cozinhaMapper.toModel(cozinhaService.adicionar(cozinha));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.remover(cozinhaId);
    }
}