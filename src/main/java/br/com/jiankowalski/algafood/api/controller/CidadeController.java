package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.CidadeMapper;
import br.com.jiankowalski.algafood.api.model.CidadeModel;
import br.com.jiankowalski.algafood.api.model.input.CidadeInput;
import br.com.jiankowalski.algafood.domain.model.Cidade;
import br.com.jiankowalski.algafood.domain.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;
    private final CidadeMapper cidadeMapper;


    public CidadeController(CidadeService cidadeService, CidadeMapper cidadeMapper) {
        this.cidadeService = cidadeService;
        this.cidadeMapper = cidadeMapper;
    }

    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeMapper.toCollectionModel(cidadeService.buscarCidades());
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        return cidadeMapper.toModel(cidadeService.buscar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        Cidade cidade = cidadeMapper.inputToDomain(cidadeInput);
        return cidadeMapper.toModel(cidadeService.adicionar(cidade));
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel alterar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
        Cidade cidadeAtual = cidadeService.buscar(cidadeId);
        cidadeMapper.update(cidadeAtual, cidadeInput);
        return cidadeMapper.toModel(cidadeService.adicionar(cidadeAtual));
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.remover(cidadeId);
    }

}