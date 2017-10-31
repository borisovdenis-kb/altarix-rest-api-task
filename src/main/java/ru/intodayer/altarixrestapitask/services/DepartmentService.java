package ru.intodayer.altarixrestapitask.services;

import ru.intodayer.altarixrestapitask.models.Department;


public interface DepartmentService {

    void addDepartment(Department newDepartment);

    void updateDepartmentName(long id, Department department);

    void deleteDepartment(long id);

}
