package br.com.jiankowalski.algafood.api.controller;

import br.com.jiankowalski.algafood.domain.filter.VendaDiariaFiltro;
import br.com.jiankowalski.algafood.domain.model.report.VendaDiaria;
import br.com.jiankowalski.algafood.domain.service.VendaQueryService;
import br.com.jiankowalski.algafood.domain.service.VendaReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {
    private final VendaQueryService vendaQueryService;
    private final VendaReportService vendaReportService;

    public EstatisticasController(VendaQueryService vendaQueryService, VendaReportService vendaReportService) {
        this.vendaQueryService = vendaQueryService;
        this.vendaReportService = vendaReportService;
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendas(VendaDiariaFiltro vendaDiariaFiltro) {
        return vendaQueryService.consultarVendaDiariaService(vendaDiariaFiltro);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasPdf(VendaDiariaFiltro vendaDiariaFiltro) {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(vendaReportService.emitirVendasDiarias(vendaDiariaFiltro));
    }
}
