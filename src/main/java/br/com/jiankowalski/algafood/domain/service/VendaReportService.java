package br.com.jiankowalski.algafood.domain.service;

import br.com.jiankowalski.algafood.domain.filter.VendaDiariaFiltro;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFiltro filtro);
}
