package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class Service400Exception extends RuntimeException {
    public Service400Exception() {
        super();
    }

    public Service400Exception(String message) {
        super(message);
    }

    public static String getDuplicateDepartmentNameMessage(String name) {
        return "Department with name [" + name + "] already exist.";
    }
}
