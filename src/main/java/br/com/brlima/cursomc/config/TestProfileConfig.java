package br.com.brlima.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brlima.cursomc.service.DBService;
import br.com.brlima.cursomc.service.mail.EmailService;
import br.com.brlima.cursomc.service.mail.MockEmailService;

@Configuration
@Profile("test")
public class TestProfileConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
