package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Department;
import java.util.List;
import java.util.Set;


public interface DepartmentService {

    void addDepartment(Department newDepartment);

    void updateDepartmentName(long id, Department department);

    void deleteDepartment(long id);

    Set<Department> getSubDepartments(long id, int level);

    String getDepartment(long id);

    Department getDepartmentByName(String name);

    Set<Department> getParentDepartments(long id);
}
