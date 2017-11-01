package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class Service403Exception extends RuntimeException {
    public Service403Exception() {
    }

    public Service403Exception(String message) {
        super(message);
    }
}
