package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.filter.VendaDiariaFiltro;
import br.com.jiankowalski.algafood.domain.model.report.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendaDiariaService(VendaDiariaFiltro filtro);
}
