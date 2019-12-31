package br.com.brlima.cursomc.service.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOGGER.info("Simulando envio de e-mail...");
        LOGGER.info(message.toString());
        LOGGER.info("Mensagem '''enviada'''");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOGGER.info("Simulando envio de e-mail...");
        LOGGER.info(message.toString());
        LOGGER.info("Mensagem '''enviada'''");
    }
}