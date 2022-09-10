package br.com.jiankowalski.algafood.infrastructure.repository.spec;

import br.com.jiankowalski.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;


public class RestauranteSpecs {

    private RestauranteSpecs() {
    }

    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        if (StringUtils.hasLength(nome)) {
            return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
        }
        return (root, query, builder) -> builder.isNotNull(root.get("nome"));
    }

}
