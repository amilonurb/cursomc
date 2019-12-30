package br.com.brlima.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brlima.cursomc.service.DBService;

@Configuration
@Profile("test")
public class TestProfileConfig {

    @Autowired
    private DBService dbService;

    public boolean instantiateDatabase() {
        dbService.instantiateTestDatabase();
        return true;
    }
}