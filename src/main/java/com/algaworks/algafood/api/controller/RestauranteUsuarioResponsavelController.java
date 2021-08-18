package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    private final RestauranteService restauranteService;
    private final UsuarioModelAssembler usuarioModelAssembler;

    public RestauranteUsuarioResponsavelController(RestauranteService restauranteService,
                                                   UsuarioModelAssembler usuarioModelAssembler) {
        this.restauranteService = restauranteService;
        this.usuarioModelAssembler = usuarioModelAssembler;
    }

    @GetMapping
    public List<UsuarioModel> listar(@PathVariable Long restauranteId) {
        return usuarioModelAssembler.toColletion(
                restauranteService.buscar(restauranteId).getUsuariosResponsaveis());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.removerResponsavelDoRestaurante(restauranteId, usuarioId);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.adicionarResponsavelDoRestaurante(restauranteId, usuarioId);
    }


}
