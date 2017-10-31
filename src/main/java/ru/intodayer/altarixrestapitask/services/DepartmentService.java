package ru.intodayer.altarixrestapitask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.services.exceptions.DuplicateDepartmentNameException;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // TODO: Solve problem with adding of subdepartment
    public void addDepartment(Department newDepartment) {
        Department rootDepartment = departmentRepository.findDepartmentByParentDepartmentIsNull();
        if (rootDepartment != null && newDepartment.getParentDepartment() == null) {
            rootDepartment.setParentDepartment(newDepartment);
        }
        departmentRepository.save(newDepartment);
    }

    public void updateDepartmentName(long id, Department department) {
        Department departmentWithName = departmentRepository.findDepartmentByName(department.getName());
        Department targetDepartment = departmentRepository.findOne(id);

        if (departmentWithName != null) {
            if(departmentWithName.getId() != targetDepartment.getId()) {
                throw new DuplicateDepartmentNameException();
            }
        }

        targetDepartment.setName(department.getName());
        departmentRepository.save(targetDepartment);
    }
}
