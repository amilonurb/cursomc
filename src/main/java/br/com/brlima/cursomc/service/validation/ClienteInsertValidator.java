package br.com.brlima.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.brlima.cursomc.model.cliente.TipoCliente;
import br.com.brlima.cursomc.model.cliente.dto.ClienteNewDTO;
import br.com.brlima.cursomc.rest.exception.FieldMessage;
import br.com.brlima.cursomc.util.DocumentUtils;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> erros = new ArrayList<>();

        if (dto.getCodigoTipoCliente().equals(TipoCliente.PESSOA_FISICA.getKey()) && !DocumentUtils.isValidCPF(dto.getCpfOuCnpj())) {
            erros.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (dto.getCodigoTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getKey()) && !DocumentUtils.isValidCNPJ(dto.getCpfOuCnpj())) {
            erros.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
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
