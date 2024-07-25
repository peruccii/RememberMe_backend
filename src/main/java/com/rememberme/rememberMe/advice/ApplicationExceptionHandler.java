package com.rememberme.rememberMe.advice;

import com.rememberme.rememberMe.exceptions.RememberMeExceptions;
import com.rememberme.rememberMe.presenters.RuntimeExceptionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * Class {@code ApplicationExceptionHandler} represent how to handle different exceptions.
 * <p>
 *  It contain specific methods for each exception based in your status code.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex)  {
       Map<String, String> errorMap = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(error -> {
           errorMap.put(error.getField(), error.getDefaultMessage());
       });

      return errorMap;
    }

    @ExceptionHandler(RememberMeExceptions.class)
    public ResponseEntity<RuntimeExceptionPresenter> handleRememberMeException(RememberMeExceptions e) {
        return e.toProblemDetail();
    }

    // TODO: OTHER METHODS
}
