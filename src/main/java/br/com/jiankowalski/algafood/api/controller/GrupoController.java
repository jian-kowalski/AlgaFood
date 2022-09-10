package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.GrupoMapper;
import br.com.jiankowalski.algafood.api.model.GrupoModel;
import br.com.jiankowalski.algafood.api.model.input.GrupoInput;
import br.com.jiankowalski.algafood.domain.model.Grupo;
import br.com.jiankowalski.algafood.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;
    private final GrupoMapper grupoMapper;

    public GrupoController(GrupoService cadastroGrupoService, GrupoMapper grupoMapper) {
        this.grupoService = cadastroGrupoService;
        this.grupoMapper = grupoMapper;
    }

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoMapper.toCollectionModel(grupoService.buscarGrupos());
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        return grupoMapper.toModel(grupoService.buscar(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoMapper.inputToDomain(grupoInput);
        return grupoMapper.toModel(grupoService.adicionar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody GrupoInput grupoInput) {
        Grupo grupo = grupoService.buscar(grupoId);
        grupoMapper.update(grupo, grupoInput);
        return grupoMapper.toModel(grupoService.adicionar(grupo));
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.remover(grupoId);
    }

}
