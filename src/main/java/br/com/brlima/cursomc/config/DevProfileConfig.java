package br.com.brlima.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brlima.cursomc.service.DBService;
import br.com.brlima.cursomc.service.mail.EmailService;
import br.com.brlima.cursomc.service.mail.SMTPEmailService;

@Configuration
@Profile("dev")
public class DevProfileConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() {
        if (!strategy.equals("create")) {
            return false;
        }
        
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SMTPEmailService();
    }
}