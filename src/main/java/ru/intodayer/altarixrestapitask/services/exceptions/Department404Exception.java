package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class Department404Exception extends RuntimeException {
    public Department404Exception() {
        super();
    }

    public Department404Exception(String message) {
        super(message);
    }
}
