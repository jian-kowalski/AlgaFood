package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.filter.VendaDiariaFiltro;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFiltro filtro);
}
