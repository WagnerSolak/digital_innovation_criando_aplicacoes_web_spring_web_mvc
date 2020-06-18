package com.globallabs.springwebmvc.rest;

import com.globallabs.springwebmvc.exception.JediNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class resourceAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND) // sempre retorno not_found
    @ExceptionHandler(JediNotFoundException.class) // quando tiver esta exceção
    public void notFound(){
    }
}
