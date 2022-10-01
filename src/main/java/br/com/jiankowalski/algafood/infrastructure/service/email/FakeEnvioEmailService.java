package br.com.jiankowalski.algafood.infrastructure.service.email;

import br.com.jiankowalski.algafood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService {


    @Override
    public void enviar(Mensagem mensagem) {
        log.info("[FAKE E-MAIL] Para: {}", mensagem.getDestinatarios());
    }
}