package ru.intodayer.altarixrestapitask.models.validators.implementations;

import org.springframework.stereotype.Component;
import ru.intodayer.altarixrestapitask.models.validators.ModelValidator;
import ru.intodayer.altarixrestapitask.services.exceptions.Service400Exception;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;


@Component
public class ModelValidatorImpl<T> implements ModelValidator<T> {

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static final String CYRILLIC_REG_EXP = "[а-яёА-ЯЁ\\-]+";

    public static final String NOT_NULL_MSG = "can not be null";

    @Override
    public void validate(T model) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(model);

        if (constraintViolations.size() > 0) {
            Set<String> violationMessages = new HashSet<>();

            for (ConstraintViolation<T> v: constraintViolations) {
                violationMessages.add(v.getMessage());
            }
            throw new Service400Exception("Validation error: " + violationMessages.toString());
        }
    }
}
