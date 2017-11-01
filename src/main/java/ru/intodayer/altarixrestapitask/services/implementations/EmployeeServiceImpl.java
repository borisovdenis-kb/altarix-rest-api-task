package ru.intodayer.altarixrestapitask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.repositories.EmployeeRepository;
import ru.intodayer.altarixrestapitask.services.EmployeeService;
import ru.intodayer.altarixrestapitask.services.exceptions.Service404Exception;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Employee getEntityIfExist(long id) {
        Employee employee = employeeRepository.findOne(id);
        if (employee == null) {
            throw new Service404Exception(
                "Department entity with id " + id + " does't exist."
            );
        }
        return employee;
    }

    @Override
    public Employee getEmployee(long id) {
        Employee employee = getEntityIfExist(id);
        return employee;
    }

    @Override
    public Employee getEmployeesChief(long id) {
        Employee employee = getEntityIfExist(id);
        Employee chief = departmentRepository.getDepartmentChief(employee.getDepartment());
        if (chief == null) {
            throw new Service404Exception(
                "Chief of employee with id " + id + " does't exist."
            );
        }
        return chief;
    }
}
