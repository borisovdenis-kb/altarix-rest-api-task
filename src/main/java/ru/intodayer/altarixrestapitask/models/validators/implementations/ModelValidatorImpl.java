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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class ModelValidatorImpl<T> implements ModelValidator<T> {

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static final String CYRILLIC_REG_EXP = "[а-яёА-ЯЁ\\-]+";

    public static final String NOT_NULL_MSG = "can not be null";

    public static void validateDoubleField(String fieldContent, String fieldName) {
        Pattern p = Pattern.compile("^(?:0|[1-9][0-9]*)\\.[0-9]+$");
        Matcher m = p.matcher(fieldContent);
        if (!m.matches()) {
            throw new Service400Exception(
                "Validation error: " + fieldName + " must be a valid double."
            );
        }
    }

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
