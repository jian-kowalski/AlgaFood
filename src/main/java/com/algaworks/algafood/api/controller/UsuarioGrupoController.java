package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    private final UsuarioService usuarioService;

    private final GrupoModelAssembler grupoModelAssembler;

    public UsuarioGrupoController(UsuarioService usuarioService,
                                  GrupoModelAssembler grupoModelAssembler) {
        this.usuarioService = usuarioService;
        this.grupoModelAssembler = grupoModelAssembler;
    }

    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        return grupoModelAssembler
                .toCollectionModel(usuarioService.buscar(usuarioId).getGrupos());
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }
}
