package br.com.brlima.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brlima.cursomc.model.pagamento.PagamentoBoleto;
import br.com.brlima.cursomc.model.pagamento.PagamentoCartao;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            @Override
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoBoleto.class);
                objectMapper.registerSubtypes(PagamentoCartao.class);
                super.configure(objectMapper);
            }
        };
    }
}
