package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class Department400Exception extends RuntimeException {
    public Department400Exception() {
        super();
    }

    public Department400Exception(String message) {
        super(message);
    }
}
