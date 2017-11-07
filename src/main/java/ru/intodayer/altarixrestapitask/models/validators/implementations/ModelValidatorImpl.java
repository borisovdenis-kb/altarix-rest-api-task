package ru.intodayer.altarixrestapitask.models.validators.implementations;

import org.springframework.stereotype.Component;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.models.validators.ModelValidator;
import ru.intodayer.altarixrestapitask.services.exceptions.Service400Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service403Exception;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Component
public class ModelValidatorImpl<T> implements ModelValidator<T> {

    public static final String CYRILLIC_REG_EXP = "[а-яёА-ЯЁ\\-]+";

    public static final String NOT_NULL_MSG = "can not be null";

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static double validateEmployeeSalaryField(String salaryStr, Employee employee, Employee chief) {
        try {
            Double salary = Double.parseDouble(salaryStr);
            if (chief != null && !employee.isChief() && salary > chief.getSalary()) {
                throw new Service400Exception(
                    "Salary of employee can not be greater than salary of his chief."
                );
            }
            return salary;
        } catch (NumberFormatException e) {
            throw new Service400Exception(
                "Validation error: salary must be a valid double."
            );
        }
    }

    public static boolean validateEmployeeIsChiefField(String isChiefStr, Employee employee, Employee chief) {
        boolean isChief = Boolean.parseBoolean(isChiefStr);
        if (chief != null && isChief && employee.getId() != chief.getId()) {
            throw new Service403Exception(
                "Chief of department id=" + chief.getDepartment().getId()  + " already exist."
            );
        }
        return isChief;
    }

    public static LocalDate validateEmployeeBirthDayField(LocalDate birthDay) {
        if (birthDay.compareTo(LocalDate.now()) > 0) {
            throw new Service400Exception(
                "Validation error: birthDay must be earlier than employmentDate."
            );
        }
        return birthDay;
    }

    public static long validateLongField(String longStr) {
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            throw new Service400Exception(
                "Validation error: long field must be valid."
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
