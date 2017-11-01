package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Department;
import java.util.List;


public interface DepartmentService {

    void addDepartment(Department newDepartment);

    void updateDepartmentName(long id, Department department);

    void deleteDepartment(long id);

    List<Department> getSubDepartments(long id, int level);

    String getDepartment(long id);
}
