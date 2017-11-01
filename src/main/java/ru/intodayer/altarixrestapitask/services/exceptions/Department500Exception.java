package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class Department500Exception extends RuntimeException {
    public Department500Exception() {
    }

    public Department500Exception(String message) {
        super(message);
    }

    public Department500Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
