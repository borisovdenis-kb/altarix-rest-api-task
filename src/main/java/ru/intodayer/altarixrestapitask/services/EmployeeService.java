package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.models.Gender;

import java.util.Set;


public interface EmployeeService {
    Employee getEmployee(long id);

    Employee getEmployeesChief(long id);

    Set<Employee> getEmployeeByGenderSalaryBirthDay(Gender gender, double salary, String birthDay);

    void updateEmployee(long id, String json);

    void dismissEmployeeFromDepartment(long depId, long empId);

    void changeEmployeesDepartment(long depId, long newDepId);

    void changeEmployeesDepartment(long depId, long empId, long newDepId);

    void addNewEmployeeToDepartment(long depId, String json);
}
