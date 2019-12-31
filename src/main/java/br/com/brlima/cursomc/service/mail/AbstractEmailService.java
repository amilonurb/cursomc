package br.com.brlima.cursomc.service.mail;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.brlima.cursomc.model.pedido.Pedido;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;
    
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
}