package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import java.util.Set;


public interface DepartmentService {

    Department addDepartment(String json);

    Department updateDepartmentName(long id, Department department);

    void deleteDepartment(long id);

    Set<Department> getAllSubDepartments(long id);

    Set<Department> getChildDepartments(long id);

    String getDepartment(long id);

    Department getDepartmentByName(String name);

    Set<Department> getParentDepartments(long id);

    String getDepartmentSalaryFund(long id);

    Department changeParentDepartment(long depId, long newDepId);
}
