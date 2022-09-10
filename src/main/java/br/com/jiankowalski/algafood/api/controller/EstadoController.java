package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.EstadoMapper;
import br.com.jiankowalski.algafood.api.model.EstadoModel;
import br.com.jiankowalski.algafood.api.model.input.EstadoInput;
import br.com.jiankowalski.algafood.domain.model.Estado;
import br.com.jiankowalski.algafood.domain.service.EstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService estadoService;

    private final EstadoMapper estadoMapper;


    public EstadoController(EstadoService estadoService, EstadoMapper estadoMapper) {
        this.estadoService = estadoService;
        this.estadoMapper = estadoMapper;
    }


    @GetMapping
    public List<EstadoModel> listar() {
        return estadoMapper.toCollectionModel(estadoService.buscarEstados());
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return estadoService.buscar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoMapper.iputToDomain(estadoInput);
        return estadoMapper.toModel(estadoService.adicionar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = estadoService.buscar(estadoId);
        estadoMapper.update(estadoAtual, estadoInput);
        return estadoMapper.toModel(estadoService.adicionar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        estadoService.remover(estadoId);
    }
}