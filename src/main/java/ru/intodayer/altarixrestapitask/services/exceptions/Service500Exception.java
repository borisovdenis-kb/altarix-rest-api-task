package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class Service500Exception extends RuntimeException {
    public Service500Exception() {
    }

    public Service500Exception(String message) {
        super(message);
    }

    public Service500Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public static String getFromJsonConvertingMessage() {
        return "Error while converting json -> map.";
    }
}
