package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.api.mapper.UsuarioMapper;
import br.com.jiankowalski.algafood.api.model.UsuarioModel;
import br.com.jiankowalski.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    private final RestauranteService restauranteService;
    private final UsuarioMapper usuarioMapper;

    public RestauranteUsuarioResponsavelController(RestauranteService restauranteService,
                                                   UsuarioMapper usuarioMapper) {
        this.restauranteService = restauranteService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public List<UsuarioModel> listar(@PathVariable Long restauranteId) {
        return usuarioMapper.toColletion(
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
