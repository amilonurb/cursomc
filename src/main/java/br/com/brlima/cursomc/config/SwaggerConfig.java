package br.com.brlima.cursomc.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage message201 = this.create201CustomMessage();
    private final ResponseMessage message204PUT = this.createSimpleMessage(204, "Atualização ok");
    private final ResponseMessage message204DELETE = this.createSimpleMessage(204, "Exclusão ok");
    private final ResponseMessage message403 = this.createSimpleMessage(403, "Não autorizado");
    private final ResponseMessage message404 = this.createSimpleMessage(404, "Não encontrado");
    private final ResponseMessage message422 = this.createSimpleMessage(422, "Erro de validação");
    private final ResponseMessage message500 = this.createSimpleMessage(500, "Erro inesperado");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(message403, message404, message500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(message201, message403, message422, message500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(message204PUT, message403, message404, message422, message500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(message204DELETE, message403, message404, message500))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API do curso Spring Boot + Ionic")
                .description("Esta API é utilizada no projeto do curso de Spring Boot do prof. Nelio Alves")
                .version("Versão 1.0")
                .termsOfServiceUrl("https://www.udemy.com/terms")
                .contact(new Contact("Nelio Alves", "udemy.com/user/nelio-alves", "nelio.cursos@gmail.com"))
                .license("Permitido uso para estudantes")
                .licenseUrl("https://www.udemy.com/terms")
                .extensions(Collections.emptyList())
                .build();
    }

    private ResponseMessage createSimpleMessage(int code, String message) {
        return new ResponseMessageBuilder().code(code).message(message).build();
    }

    private ResponseMessage create201CustomMessage() {
        Map<String, Header> headers = new HashMap<>();
        headers.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
        return new ResponseMessageBuilder().code(201).message("Recurso criado").headersWithDescription(headers).build();
    }
}
