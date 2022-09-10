package br.com.jiankowalski.algafood.infrastructure.service.query;

import br.com.jiankowalski.algafood.domain.filter.VendaDiariaFiltro;
import br.com.jiankowalski.algafood.domain.model.Pedido;
import br.com.jiankowalski.algafood.domain.model.StatusPedido;
import br.com.jiankowalski.algafood.domain.model.report.VendaDiaria;
import br.com.jiankowalski.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
class VendaQueryServiceImpl implements VendaQueryService {

    public static final String DATA_CRIACAO = "dataCriacao";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendaDiariaService(VendaDiariaFiltro filtro) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var functionDataCriacao = builder.function("date", Date.class, root.get(DATA_CRIACAO));
        var selection = builder.construct(VendaDiaria.class,
                functionDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));
        query.select(selection);
        query.where(getWhere(builder, root, filtro).toArray(new Predicate[0]));
        query.groupBy(functionDataCriacao);

        return manager.createQuery(query).getResultList();
    }

    private ArrayList<Predicate> getWhere(CriteriaBuilder builder, Root<Pedido> root, VendaDiariaFiltro filtro) {
        var predicates = new ArrayList<Predicate>();
        predicates.add(builder.and(root.get("status").in(
                StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE)));
        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(DATA_CRIACAO),
                    filtro.getDataCriacaoInicio()));
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get(DATA_CRIACAO),
                    filtro.getDataCriacaoFim()));
        }
        return predicates;
    }


}
