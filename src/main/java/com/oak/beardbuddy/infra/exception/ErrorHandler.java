package com.oak.beardbuddy.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerError400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(ErrorValidationDTO::new).toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleError409(DataIntegrityViolationException ex) {
        String errorMessage = ex.getCause().getMessage();
        String field = extractField(errorMessage);

        if (errorMessage.contains("Duplicate entry")){
            errorMessage = "Campo duplicado.";
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(field, errorMessage));
    }

    private String extractField(String errorMessage) {
        Pattern pattern = Pattern.compile("for key '(.*?)'");
        Matcher matcher = pattern.matcher(errorMessage);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "unknown";
    }
}
