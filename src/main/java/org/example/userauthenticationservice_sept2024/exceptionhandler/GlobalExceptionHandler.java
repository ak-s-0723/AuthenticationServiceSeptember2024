package org.example.userauthenticationservice_sept2024.exceptionhandler;

import org.example.userauthenticationservice_sept2024.dtos.RequestStatus;
import org.example.userauthenticationservice_sept2024.dtos.ValidationErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDto> parameterNotValid(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ValidationErrorResponseDto.builder()
                .errorField(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField())
                .errorMessage(ex.getBindingResult().getFieldError().getDefaultMessage())
                .errorValue((ex.getBindingResult().getFieldError().getRejectedValue() != null) ? ex.getBindingResult().getFieldError().getRejectedValue().toString() : "")
                .responseStatus(RequestStatus.FAILURE)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
