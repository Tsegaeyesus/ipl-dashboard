package com.dreamtech.ipldb.exception;


import com.dreamtech.ipldb.dto.ErroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErroResponse> bb(Exception exception){

        ErroResponse erroResponse=new ErroResponse();
        erroResponse.setName("Team Nf");
        erroResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(erroResponse);
    }
}
