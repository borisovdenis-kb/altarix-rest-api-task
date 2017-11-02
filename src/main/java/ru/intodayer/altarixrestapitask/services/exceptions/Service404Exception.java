package ru.intodayer.altarixrestapitask.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class Service404Exception extends RuntimeException {
    public Service404Exception() {
        super();
    }

    public Service404Exception(String message) {
        super(message);
    }

    public static String getPositionDoesNotExistMessage(long id) {
        String positionDoesNotExist = "Position entity with id=%s does not exist.";
        return String.format(positionDoesNotExist, id);
    }

    public static String getEmployeeDoesNotExistMessage(long id) {
        String employeeDoesNotExist = "Employee entity with id=%s does not exist.";
        return String.format(employeeDoesNotExist, id);
    }

    public static String getDepartmentDoesNotExistMessage(long id) {
        String departmentDoesNotExist = "Department entity with id=%s does not exist.";
        return String.format(departmentDoesNotExist, id);
    }

    public static String getEmpWoringInDepDoesNotExistMessage(long empId, long depId) {
        String empWoringInDepDoesNotExist = "Employee id=%s working in department id=%s does not exist.";
        return String.format(empWoringInDepDoesNotExist, empId, depId);
    }
}
