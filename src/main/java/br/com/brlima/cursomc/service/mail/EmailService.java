package br.com.brlima.cursomc.service.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.model.pedido.Pedido;

public interface EmailService {

    void sendOrderConfirmationMail(Pedido pedido);

    void sendEmail(SimpleMailMessage message);

    void sendOrderConfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage message);

	void sendNewPasswordEmail(Cliente cliente, String newPassword);
}