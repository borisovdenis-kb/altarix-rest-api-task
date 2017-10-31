package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// TODO: Add a more informative message.

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Department with that name already exist.")
public class DuplicateDepartmentNameException extends RuntimeException {
    public DuplicateDepartmentNameException() {
        super();
    }
}
