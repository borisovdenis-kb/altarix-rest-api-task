package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class Service403Exception extends RuntimeException {
    public Service403Exception() {
    }

    public Service403Exception(String message) {
        super(message);
    }
}
