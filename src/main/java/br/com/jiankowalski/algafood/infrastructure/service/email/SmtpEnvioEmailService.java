package br.com.jiankowalski.algafood.infrastructure.service.email;

import br.com.jiankowalski.algafood.core.email.EmailProperties;
import br.com.jiankowalski.algafood.domain.exception.EmailException;
import br.com.jiankowalski.algafood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties properties;

    private final Configuration freeMarkerConfig;

    public SmtpEnvioEmailService(EmailProperties properties, JavaMailSender mailSender, Configuration freeMarkerConfig) {
        this.mailSender = mailSender;
        this.properties = properties;
        this.freeMarkerConfig = freeMarkerConfig;
    }

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            var corpo = processarTemplate(mensagem);
            var mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(properties.getRemetente());
            helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            helper.setSubject(mensagem.getAssunto());
            helper.setText(corpo, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possivel enviar o e-mail", e);
        }
    }

    private String processarTemplate(Mensagem mensagem) {
        try {
            var template = freeMarkerConfig.getTemplate(mensagem.getCorpo());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possivel montar o template do e-mail", e);
        }
    }
}
