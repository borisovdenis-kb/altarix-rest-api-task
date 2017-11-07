package ru.intodayer.altarixrestapitask.dto;

import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;


public interface DtoConverter {

    EmployeeDto convertEmployeeToDto(Employee employee);

    DepartmentDto convertDepartmentToDto(Department department);

}
