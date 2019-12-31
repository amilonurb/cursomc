package br.com.brlima.cursomc.service.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SMTPEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SMTPEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOGGER.info("Enviando e-mail...");
        mailSender.send(message);
        LOGGER.info("Mensagem enviada");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOGGER.info("Enviando e-mail...");
        javaMailSender.send(message);
        LOGGER.info("Mensagem enviada");
    }
    
}