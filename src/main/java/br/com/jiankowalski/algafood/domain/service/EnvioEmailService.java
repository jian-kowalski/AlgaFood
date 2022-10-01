package br.com.jiankowalski.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem {
        private Set<String> destinatarios;
        private String assunto;
        private String corpo;
        private Map<String, Object> variaveis;
    }
}
