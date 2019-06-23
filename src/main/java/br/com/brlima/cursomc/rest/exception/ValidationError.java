package br.com.brlima.cursomc.rest.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> validationMessages = new ArrayList<>();

    public ValidationError(Integer httpStatus, String message, Long timestamp) {
        super(httpStatus, message, timestamp);
    }

    public List<FieldMessage> getValidationMessages() {
        return validationMessages;
    }

    public void addValidationMessage(String fieldName, String errorMessage) {
        this.validationMessages.add(new FieldMessage(fieldName, errorMessage));
    }
}
