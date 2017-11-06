package ru.intodayer.altarixrestapitask.models.validators;


public interface ModelValidator<T> {
    void validate(T model);
}
