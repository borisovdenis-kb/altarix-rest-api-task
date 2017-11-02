package ru.intodayer.altarixrestapitask.services;


import ru.intodayer.altarixrestapitask.models.Employee;

public interface EmployeeService {
    Employee getEmployee(long id);

    Employee getEmployeesChief(long id);

    void deleteEmployeeFromDepartment(long depId, long empId);

    void changeEmployeesDepartment(long depId, long empId, long newDepId);
}
