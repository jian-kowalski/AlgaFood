package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.filter.VendaDiariaFiltro;
import com.algaworks.algafood.domain.model.report.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendaDiariaService(VendaDiariaFiltro filtro);
}
