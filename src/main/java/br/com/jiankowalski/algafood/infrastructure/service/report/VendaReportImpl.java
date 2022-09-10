package br.com.jiankowalski.algafood.infrastructure.service.report;

import br.com.jiankowalski.algafood.domain.filter.VendaDiariaFiltro;
import br.com.jiankowalski.algafood.domain.service.VendaQueryService;
import br.com.jiankowalski.algafood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class VendaReportImpl implements VendaReportService {
    private final VendaQueryService vendaQueryService;

    public VendaReportImpl(VendaQueryService vendaQueryService) {
        this.vendaQueryService = vendaQueryService;
    }

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFiltro filtro) {
        var inputStream = this.getClass().getResourceAsStream("/reports/vendas-diarias.jasper");
        try {
            return JasperExportManager
                    .exportReportToPdf(JasperFillManager
                            .fillReport(inputStream, getParams(), getDataSource(filtro)));
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
        }
    }

    private JRDataSource getDataSource(VendaDiariaFiltro filtro) {
        return new JRBeanCollectionDataSource(vendaQueryService.consultarVendaDiariaService(filtro));
    }

    private Map<String, Object> getParams() {
        var paramentros = new HashMap<String, Object>();
        paramentros.put("REPORT_LOCALE", new Locale("PT", "BR"));
        return paramentros;
    }
}
