package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class Department403Exception extends RuntimeException {
    public Department403Exception() {
    }

    public Department403Exception(String message) {
        super(message);
    }
}
