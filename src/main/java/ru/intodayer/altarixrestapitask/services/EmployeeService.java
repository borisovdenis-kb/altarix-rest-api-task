package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.models.Gender;
import java.util.Set;


public interface EmployeeService {
    Employee getEmployee(long id);

    Set<Employee> getDepartmentEmployees(long depId);

    Employee getEmployeesChief(long id);

    Set<Employee> getEmployeeByGenderSalaryBirthDay(Gender gender, double salary, String birthDay);

    void updateEmployee(long id, String employeeJson);

    void dismissEmployeeFromDepartment(long depId, long empId);

    void transferAllEmployeesToAnotherDepartment(long depId, long newDepId);

    void transferEmployeeToAnotherDepartment(long depId, long empId, long newDepId);

    void addNewEmployeeToDepartment(long depId, String employeeJson);
}
