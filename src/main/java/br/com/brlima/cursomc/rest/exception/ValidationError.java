package br.com.brlima.cursomc.rest.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> validationMessages = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getValidationMessages() {
        return validationMessages;
    }

    public void addValidationMessage(String fieldName, String errorMessage) {
        this.validationMessages.add(new FieldMessage(fieldName, errorMessage));
    }
}
