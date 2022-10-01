package br.com.jiankowalski.algafood.core.email;

import br.com.jiankowalski.algafood.domain.service.EnvioEmailService;
import br.com.jiankowalski.algafood.infrastructure.service.email.FakeEnvioEmailService;
import br.com.jiankowalski.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@org.springframework.context.annotation.Configuration
public class EmailConfig {

    private final EmailProperties emailProperties;

    public EmailConfig(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public EnvioEmailService envioEmailService() {
        if (EmailProperties.TipoEnvio.DEV.equals(emailProperties.getTipoEnvio())) {
            return new FakeEnvioEmailService();
        }
        return new SmtpEnvioEmailService(emailProperties, mailSender(), freeMakerConfig());
    }

    private Configuration freeMakerConfig() {
        return new Configuration(new Version("2.3.0"));
    }


    private JavaMailSender mailSender() {
        return new JavaMailSenderImpl();
    }
}
