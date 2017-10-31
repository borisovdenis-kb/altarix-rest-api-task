package ru.intodayer.altarixrestapitask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.services.DepartmentService;
import ru.intodayer.altarixrestapitask.services.exceptions.Department400Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Department404Exception;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department getDepartmentIfExist(long id) {
        Department department = departmentRepository.findOne(id);
        if (department == null) {
            throw new Department404Exception(
                "Department entity with id " + id + " does't exist."
            );
        }
        return department;
    }

    // TODO: Solve problem with adding of subdepartment
    @Override
    public void addDepartment(Department newDepartment) {
        Department rootDepartment = departmentRepository.findDepartmentByParentDepartmentIsNull();
        if (rootDepartment != null && newDepartment.getParentDepartment() == null) {
            rootDepartment.setParentDepartment(newDepartment);
        }
        departmentRepository.save(newDepartment);
    }

    @Override
    public void updateDepartmentName(long id, Department department) {
        Department targetDepartment = getDepartmentIfExist(id);
        Department departmentWithName = departmentRepository.findDepartmentByName(department.getName());

        if (departmentWithName != null) {
            if(departmentWithName.getId() != targetDepartment.getId()) {
                throw new Department400Exception(
                    "Department with that name already exist."
                );
            }
        }

        targetDepartment.setName(department.getName());
        departmentRepository.save(targetDepartment);
    }

    @Override
    public void deleteDepartment(long id) {
        Department department = getDepartmentIfExist(id);

        if (department.getEmployees().size() == 0) {
            for (Department child: department.getChildDepartments()) {
                child.setParentDepartment(null);
            }
            departmentRepository.delete(department);
        } else {
            throw new Department400Exception(
                "Department with at least one employee can not be deleted."
            );
        }
    }

    @Override
    public List<Department> getSubDepartments(long id, int level) {
        Department department = getDepartmentIfExist(id);

        if (level == 1) {
            return department.getChildDepartments();
        } else {
            throw new Department400Exception(
                "Service is not yet able to return sub-departments to a level deeper than 1."
            );
        }
    }
}
