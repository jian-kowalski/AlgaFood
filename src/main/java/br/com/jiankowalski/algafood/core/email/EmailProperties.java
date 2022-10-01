package br.com.jiankowalski.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Component
@ConfigurationProperties("algafood.email")
@Getter
@Setter
public class EmailProperties {

    @NotNull
    private String remetente;

    private TipoEnvio tipoEnvio = TipoEnvio.DEV;

    enum TipoEnvio {
        DEV,
        PROD
    }
}
