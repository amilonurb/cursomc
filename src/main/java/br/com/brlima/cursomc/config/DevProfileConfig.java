package br.com.brlima.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brlima.cursomc.service.DBService;

@Configuration
@Profile("dev")
public class DevProfileConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    public boolean instantiateDatabase() {
        if (!strategy.equals("create")) {
            return false;
        }
        
        dbService.instantiateTestDatabase();
        return true;
    }
}