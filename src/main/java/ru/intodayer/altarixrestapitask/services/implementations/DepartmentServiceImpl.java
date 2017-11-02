package ru.intodayer.altarixrestapitask.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.services.DepartmentService;
import ru.intodayer.altarixrestapitask.services.exceptions.Service400Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service403Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service404Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service500Exception;
import java.util.*;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department getEntityIfExist(long id) {
        Department department = departmentRepository.findOne(id);
        if (department == null) {
            throw new Service404Exception(
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
        Department targetDepartment = getEntityIfExist(id);
        Department departmentWithName = departmentRepository.findDepartmentByName(department.getName());

        if (departmentWithName != null) {
            if(departmentWithName.getId() != targetDepartment.getId()) {
                throw new Service400Exception(
                    "Department with name " + departmentWithName.getName() + " already exist."
                );
            }
        }

        targetDepartment.setName(department.getName());
        departmentRepository.save(targetDepartment);
    }

    /**
     * It is assumed that when a department is deleted, all sub-departments
     * are not deleted cascaded, but simply lose the reference to the parent.
     * And then the client for each sub-department sets new parent department.
     */
    @Override
    public void deleteDepartment(long id) {
        Department department = getEntityIfExist(id);

        if (department.getEmployees().size() == 0) {
            for (Department child: department.getChildDepartments()) {
                child.setParentDepartment(null);
            }
            department.setParentDepartment(null);
            departmentRepository.delete(department);
        } else {
            throw new Service403Exception(
                "Department with at least one employee can not be deleted."
            );
        }
    }

    @Override
    public Set<Department> getSubDepartments(long id) {
        Department department = getEntityIfExist(id);
        Set<Department> allSubDepartments = new HashSet<>();
        Stack<Department> stack = new Stack<>();

        stack.addAll(department.getChildDepartments());
        while (!stack.empty()) {
            Department top = stack.pop();
            if (!allSubDepartments.contains(top)) {
                allSubDepartments.add(top);
                stack.addAll(top.getChildDepartments());
            }
        }
        return allSubDepartments;
    }

    @Override
    public Set<Department> getSubDepartments(long id, int level) {
        Department department = getEntityIfExist(id);

        if (level == 1) {
            return department.getChildDepartments();
        } else {
            throw new Service400Exception(
                "Service is not yet able to return sub-departments to a level deeper than 1."
            );
        }
    }

    @Override
    public String getDepartment(long id) {
        Department department = getEntityIfExist(id);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonConstructor = new HashMap<>();
        Map<String, Object> departmentMap = new HashMap<>();

        departmentMap.put("name", department.getName());
        departmentMap.put("createDate", department.getCreateDate());
        jsonConstructor.put("department", departmentMap);
        jsonConstructor.put("departmentChief", departmentRepository.getDepartmentChief(department));
        jsonConstructor.put("employeesAmount", department.getEmployees().size());

        try {
            return mapper.writeValueAsString(jsonConstructor);
        } catch (JsonProcessingException e) {
            throw new Service500Exception(
                "Error while converting map -> json.", e
            );
        }
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department department = departmentRepository.findDepartmentByName(name);
        if (department == null) {
            throw new Service404Exception(
                "Department with name " + name + " does not exist."
            );
        }
        return department;
    }

    @Override
    public Set<Department> getParentDepartments(long id) {
        Department currentDep = getEntityIfExist(id);
        currentDep = currentDep.getParentDepartment();

        Set<Department> parentDepartments = new HashSet<>();
        while (currentDep != null) {
            parentDepartments.add(currentDep);
            currentDep = currentDep.getParentDepartment();
        }
        return parentDepartments;
    }

    @Override
    public String getDepartmentFund(long id) {
        Department department = getEntityIfExist(id);
        Double sum = department.getEmployees()
            .stream()
            .mapToDouble((e) -> e.getSalary())
            .sum();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonConstructor = new HashMap<>();
        jsonConstructor.put("departmentFund", sum);

        try {
            return mapper.writeValueAsString(jsonConstructor);
        } catch (JsonProcessingException e) {
            throw new Service500Exception(
                "Error while converting map -> json.", e
            );
        }
    }

    @Override
    public Set<Employee> getDepartmentEmployees(long id) {
        Department department = getEntityIfExist(id);
        return department.getEmployees();
    }
}
