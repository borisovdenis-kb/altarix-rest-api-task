package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Department;
import java.util.Set;


public interface DepartmentService {

    Department addDepartment(String departmentJson);

    Department updateDepartmentName(long id, Department department);

    void deleteDepartment(long id);

    Set<Department> getAllSubDepartments(long id);

    Set<Department> getChildDepartments(long id);

    Department getDepartment(long id);

    Department getDepartmentByName(String name);

    Set<Department> getParentDepartments(long id);

    String getDepartmentSalaryFund(long id);

    Department changeParentDepartment(long depId, long newDepId);
}
