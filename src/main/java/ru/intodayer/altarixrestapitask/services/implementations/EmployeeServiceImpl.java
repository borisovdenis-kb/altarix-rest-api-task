package ru.intodayer.altarixrestapitask.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.repositories.EmployeeRepository;
import ru.intodayer.altarixrestapitask.services.EmployeeService;
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
                Service404Exception.getEmployeeDoesNotExistMessage(id)
            );
        }
        return employee;
    }

    @Override
    public Employee getEmployee(long id) {
        return getEntityIfExist(id);
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
        Employee employee = employeeRepository.getDismissableEmployee(depId, empId);

        if (employee == null) {
            throw new Service404Exception(
                "Dismissable " + Service404Exception.getEmpWoringInDepDoesNotExistMessage(empId, depId)
            );
        }

        employee.setDepartment(null);
        employee.setDismissalDate(new Date());
        employeeRepository.save(employee);
    }

    @Override
    public void changeEmployeesDepartment(long depId, long empId, long newDepId) {
        Employee employee = employeeRepository.findEmployeeByIdAndDepartmentId(empId, depId);
        Department newDepartment = departmentRepository.findOne(newDepId);

        if (employee == null) {
            throw new Service404Exception(
                Service404Exception.getEmpWoringInDepDoesNotExistMessage(empId, depId)
            );
        }

        if (newDepartment == null) {
            throw new Service404Exception(
                Service404Exception.getDepartmentDoesNotExistMessage(newDepId)
            );
        }

        employee.setDepartment(newDepartment);
        employeeRepository.save(employee);
    }
}
