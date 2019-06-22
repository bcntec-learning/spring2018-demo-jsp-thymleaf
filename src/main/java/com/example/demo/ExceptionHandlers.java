package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {


    @ResponseStatus(HttpStatus.NOT_FOUND )
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public ErrorResponse handleNoSuchElementException(final NoSuchElementException ex) {
        return new ErrorResponse("NOT_FOUND", "No encontrado");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        log.error("Unexpected error", ex);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occured");
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private final String code;
        private final String message;
    }

}