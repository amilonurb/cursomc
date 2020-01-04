package br.com.brlima.cursomc.rest.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.brlima.cursomc.service.exception.AuthorizationException;
import br.com.brlima.cursomc.service.exception.DataIntegrityException;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleObjectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> handleDataIntegrity(DataIntegrityException exception, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.stream().forEach(fe -> validationError.addValidationMessage(fe.getField(), fe.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> handleAuthorization(AuthorizationException exception, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
