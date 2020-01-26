package br.com.brlima.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.brlima.cursomc.model.cliente.dto.ClienteNewDTO;
import br.com.brlima.cursomc.model.enums.TipoCliente;
import br.com.brlima.cursomc.repository.ClienteRepository;
import br.com.brlima.cursomc.rest.exception.FieldMessage;
import br.com.brlima.cursomc.util.DocumentUtils;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> erros = new ArrayList<>();

        if (dto.getCodigoTipoCliente().equals(TipoCliente.PESSOA_FISICA.getKey())
                && !DocumentUtils.isValidCPF(dto.getCpfOuCnpj())) {
            erros.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (dto.getCodigoTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getKey())
                && !DocumentUtils.isValidCNPJ(dto.getCpfOuCnpj())) {
            erros.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            erros.add(new FieldMessage("email", "E-mail já existente"));
        }

        erros.forEach(fm -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fm.getErrorMessage())//
                    .addPropertyNode(fm.getFieldName())//
                    .addConstraintViolation();
        });
        return erros.isEmpty();
    }
}
