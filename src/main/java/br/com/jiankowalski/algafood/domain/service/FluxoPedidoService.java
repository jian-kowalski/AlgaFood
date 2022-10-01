package br.com.jiankowalski.algafood.domain.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

@Service
public class FluxoPedidoService {

    private final PedidoService pedidoService;
    private final EnvioEmailService envioEmailService;

    public FluxoPedidoService(PedidoService pedidoService, EnvioEmailService envioEmailService) {
        this.pedidoService = pedidoService;
        this.envioEmailService = envioEmailService;
    }

    @Transactional
    public void confirmar(String codigoPedido) {
        var pedido = pedidoService.buscar(codigoPedido);
        pedido.confirmar();
        var msg = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome())
                .corpo("pedido-confirmado.html")
                .variaveis(new HashMap<>(Collections.singletonMap("pedido", pedido)))
                .destinatarios(new HashSet<>(Collections.singletonList(pedido.getCliente().getEmail())))
                .build();
        envioEmailService.enviar(msg);
    }

    @Transactional
    public void entregar(String codigoPedido) {
        var pedido = pedidoService.buscar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        var pedido = pedidoService.buscar(codigoPedido);
        pedido.cancelar();
    }


}
