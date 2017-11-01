package ru.intodayer.altarixrestapitask.services;


import ru.intodayer.altarixrestapitask.models.Employee;

public interface EmployeeService {
    Employee getEmployee(long id);

    Employee getEmployeesChief(long id);
}
