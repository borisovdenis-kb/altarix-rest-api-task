package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import java.util.Set;


public interface DepartmentService {

    void addDepartment(String json);

    void updateDepartmentName(long id, Department department);

    void deleteDepartment(long id);

    Set<Department> getSubDepartments(long id);

    Set<Department> getSubDepartments(long id, int level);

    String getDepartment(long id);

    Department getDepartmentByName(String name);

    Set<Department> getParentDepartments(long id);

    String getDepartmentFund(long id);

    Set<Employee> getDepartmentEmployees(long id);

    void changeParentDepartment(long depId, long newDepId);
}
