package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class Service404Exception extends RuntimeException {
    public Service404Exception() {
        super();
    }

    public Service404Exception(String message) {
        super(message);
    }
}
