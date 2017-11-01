package ru.intodayer.altarixrestapitask.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.repositories.EmployeeRepository;
import ru.intodayer.altarixrestapitask.services.EmployeeService;
import ru.intodayer.altarixrestapitask.services.exceptions.Service403Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service404Exception;
import java.util.Date;


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
                "Employee entity with id " + id + " does't exist."
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

    @Override
    public void deleteEmployeeFromDepartment(long depId, long empId) {
        Employee employee = getEntityIfExist(empId);
        Department department = departmentRepository.findOne(depId);

        if (department == null) {
            throw new Service404Exception(
                "Department entity with id " + depId + " does't exist."
            );
        }

        if(employee.getDismissalDate() != null) {
            throw new Service403Exception(
                "Employee with id " + empId + " has already been dismissed."
            );
        }

        if (employee.getDepartment().getId() != depId) {
            throw new Service403Exception(
                "Employee with id " + empId + " doesn't work in department with id " + depId
            );
        }
        employee.setDepartment(null);
        employee.setDismissalDate(new Date());
        employeeRepository.save(employee);
    }
}
