package br.com.brlima.cursomc.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target(value = { ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ClienteInsert {
    
    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}