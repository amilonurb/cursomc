package br.com.brlima.cursomc.service.mail;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.model.pedido.Pedido;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOrderConfirmationMail(Pedido pedido) {
        SimpleMailMessage mailMessage = prepareSimpleMailMessageFromPedido(pedido);
        this.sendEmail(mailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Pedido confirmado! Código: " + pedido.getId());
        mailMessage.setTo(pedido.getCliente().getEmail());
        mailMessage.setFrom(sender);
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText(pedido.toString());
        return mailMessage;
    }

    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            MimeMessage message = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(message);
        } catch (MessagingException e) {
            sendOrderConfirmationMail(pedido);
        }
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("Pedido confirmado! Código: " + pedido.getId());
        helper.setTo(pedido.getCliente().getEmail());
        helper.setFrom(sender);
        helper.setSentDate(new Date(System.currentTimeMillis()));
        helper.setText(this.htmlFromTemplatePedido(pedido), true);
        return null;
    }

    @Override
    public void sendNewPasswordEmail(Cliente cliente, String password) {
        SimpleMailMessage mailMessage = prepareNewPasswordEmail(cliente, password);
        this.sendEmail(mailMessage);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String password) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Solicitação de nova senha");
        mailMessage.setTo(cliente.getEmail());
        mailMessage.setFrom(sender);
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText("Nova senha: " + password);
        return mailMessage;
    }
}
