package br.com.brlima.cursomc.config.security.auth;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.service.ClienteService;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;
import br.com.brlima.cursomc.service.mail.EmailService;

@Service
public class AuthService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = clienteService.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("E-mail não encontrado");
        }

        String newPassword = this.generateRandomPassword();
        cliente.setPassword(passwordEncoder.encode(newPassword));

        clienteService.update(cliente);

        emailService.sendNewPasswordEmail(cliente, newPassword);
    }

    private String generateRandomPassword() {
        char[] text = new char[10];
        for (int i = 0; i < text.length; i++) {
            text[i] = this.getRandomChar();
        }
        return new String(text);
    }

    private char getRandomChar() {
        int next = random.nextInt(3);
        switch (next) {
            case 0:// dígito
                return (char) (random.nextInt(10) + 48);
            case 1:// letra maiúscula
                return (char) (random.nextInt(26) + 65);
            default:// letra minúscula
                return (char) (random.nextInt(26) + 97);
        }
    }
}
