package br.com.brlima.cursomc.service.mail;

import org.springframework.mail.SimpleMailMessage;

import br.com.brlima.cursomc.model.pedido.Pedido;

public interface EmailService {
    
    void sendOrderConfirmationMail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);
}