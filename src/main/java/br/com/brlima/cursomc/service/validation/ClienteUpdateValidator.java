package br.com.brlima.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.model.cliente.dto.ClienteDTO;
import br.com.brlima.cursomc.rest.exception.FieldMessage;
import br.com.brlima.cursomc.service.ClienteService;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteService clienteService;

    @SuppressWarnings("unchecked")
    @Override
    public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> erros = new ArrayList<>();

        Cliente cliente = clienteService.findByEmail(dto.getEmail());

        Map<String, String> atributos = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriID = Long.parseLong(atributos.get("id"));

        if (cliente != null && !cliente.getId().equals(uriID)) {
            erros.add(new FieldMessage("email", "Email já existente"));
        }

        erros.forEach(fm -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fm.getErrorMessage())
                    .addPropertyNode(fm.getFieldName())
                    .addConstraintViolation();
        });
        return erros.isEmpty();
    }
}
