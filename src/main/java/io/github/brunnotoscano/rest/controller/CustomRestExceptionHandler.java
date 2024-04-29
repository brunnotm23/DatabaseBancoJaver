package io.github.brunnotoscano.rest.controller;

import io.github.brunnotoscano.rest.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){

        List<String> erros = new ArrayList<String>();

        for(FieldError erro : ex.getBindingResult().getFieldErrors()){
            erros.add(erro.getField() + ": " + erro.getDefaultMessage());
        }

        for(ObjectError erro : ex.getBindingResult().getGlobalErrors()){
            erros.add(erro.getObjectName() + ": " + erro.getDefaultMessage());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), erros);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);

    }
}
