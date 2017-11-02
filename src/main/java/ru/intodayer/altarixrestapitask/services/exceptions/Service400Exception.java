package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class Service400Exception extends RuntimeException {
    public Service400Exception() {
        super();
    }

    public Service400Exception(String message) {
        super(message);
    }

    public static String getDublicateDepartmentNameMessage(String name) {
        return "Department with name " + name + " already exist.";
    }
}
