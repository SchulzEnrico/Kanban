package de.es.kanban.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
            (NoSuchElementException.class)
    @ResponseStatus
            (HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoSuchElementException(NoSuchElementException exception) {

        return new ErrorMessage(exception.getMessage());
    }
}
