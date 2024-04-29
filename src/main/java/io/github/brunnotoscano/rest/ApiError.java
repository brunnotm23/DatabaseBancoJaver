package io.github.brunnotoscano.rest;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiError {

    private HttpStatus status;
    private List<String> erros;

    public ApiError(HttpStatus status, List<String> erros) {
        super();
        this.status = status;
        this.erros = erros;
    }

    public ApiError(HttpStatus status, String erro){
        this.status = status;
        erros = Arrays.asList(erro);
    }


}
