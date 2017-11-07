package ru.intodayer.altarixrestapitask.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.services.DepartmentService;
import ru.intodayer.altarixrestapitask.services.exceptions.Service400Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service403Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service404Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service500Exception;
import java.io.IOException;
import java.util.*;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department getEntityIfExist(long id) {
        Department department = departmentRepository.findOne(id);
        if (department == null) {
            throw new Service404Exception(
                Service404Exception.getDepartmentDoesNotExistMessage(id)
            );
        }
        return department;
    }

    private Map<String, Object> getMapFromJsonString(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<Map<String, String>>(){});
    }

    @Override
    public Department updateDepartmentName(long id, Department updatedDepartment) {
        Department targetDepartment = getEntityIfExist(id);
        Department departmentWithName = departmentRepository.findDepartmentByName(updatedDepartment.getName());

        if (departmentWithName != null) {
            if(departmentWithName.getId() != targetDepartment.getId()) {
                throw new Service400Exception(
                    Service400Exception.getDuplicateDepartmentNameMessage(departmentWithName.getName())
                );
            }
        }

        targetDepartment.setName(updatedDepartment.getName());
        departmentRepository.save(targetDepartment);

        return targetDepartment;
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
    public Set<Department> getAllSubDepartments(long id) {
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
    public Set<Department> getChildDepartments(long id) {
        Department department = getEntityIfExist(id);
        return department.getChildDepartments();
    }

    @Override
    public Department getDepartment(long id) {
        return getEntityIfExist(id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department department = departmentRepository.findDepartmentByName(name);
        if (department == null) {
            throw new Service404Exception(
                "Department with name [" + name + "] does not exist."
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
    public String getDepartmentSalaryFund(long id) {
        Department department = getEntityIfExist(id);
        Double sum = departmentRepository.getDepartmentFund(department);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonConstructor = new HashMap<>();
        jsonConstructor.put("departmentFund", sum != null ? sum : 0);

        try {
            return mapper.writeValueAsString(jsonConstructor);
        } catch (JsonProcessingException e) {
            throw new Service500Exception(
                "Error while converting map -> json.", e
            );
        }
    }

    @Override
    public Department changeParentDepartment(long depId, long newParentDepId) {
        Department department = getEntityIfExist(depId);
        Department newParentDepartment = getEntityIfExist(newParentDepId);
        department.setParentDepartment(newParentDepartment);
        departmentRepository.save(department);

        return department;
    }

    // TODO: may be change approach of creating department
    @Override
    public Department addDepartment(String json) {
        try {
            Map<String, Object> jsonMap = getMapFromJsonString(json);
            String name = (String) jsonMap.get("name");

            if (departmentRepository.findDepartmentByName(name) != null) {
                throw new Service400Exception(
                        Service400Exception.getDuplicateDepartmentNameMessage(name)
                );
            }

            Department newDepartment = new Department(
                name,
                null,
                null
            );

            if (!jsonMap.containsKey("parentDepartmentId")) {
                Department rootDepartment = departmentRepository.getRootDepartment();
                if (rootDepartment != null) {
                    rootDepartment.setParentDepartment(newDepartment);
                }
            } else {
                Long newDepId = Long.parseLong((String) jsonMap.get("parentDepartmentId"));
                Department parentDep = getEntityIfExist(newDepId);
                newDepartment.setParentDepartment(parentDep);
            }

            departmentRepository.save(newDepartment);
            return newDepartment;
        } catch (IOException e) {
            throw new Service500Exception(
                Service500Exception.getFromJsonConvertingMessage(), e
            );
        }
    }
}
