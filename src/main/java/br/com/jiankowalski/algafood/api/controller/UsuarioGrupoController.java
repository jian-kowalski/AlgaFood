package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.GrupoMapper;
import br.com.jiankowalski.algafood.api.model.GrupoModel;
import br.com.jiankowalski.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    private final UsuarioService usuarioService;
    
    private final GrupoMapper grupoMapper;

    public UsuarioGrupoController(UsuarioService usuarioService, GrupoMapper grupoMapper) {
        this.usuarioService = usuarioService;
        this.grupoMapper = grupoMapper;
    }

    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        return grupoMapper
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
