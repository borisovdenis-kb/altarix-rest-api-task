package ru.intodayer.altarixrestapitask.services.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.models.Gender;
import ru.intodayer.altarixrestapitask.models.Position;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.repositories.EmployeeRepository;
import ru.intodayer.altarixrestapitask.repositories.PositionRepository;
import ru.intodayer.altarixrestapitask.services.EmployeeService;
import ru.intodayer.altarixrestapitask.services.exceptions.Service403Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service404Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service500Exception;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    private Map<String, Object> getMapFromJsonString(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<Map<String, String>>(){});
    }

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
        employee.setDismissalDate(LocalDate.now());
        employeeRepository.save(employee);
    }

    @Override
    public void changeEmployeesDepartment(long depId, long newDepId) {
        Department department = departmentRepository.findOne(depId);
        Department newDepartment = departmentRepository.findOne(newDepId);

        if (department == null) {
            throw new Service404Exception(
                Service404Exception.getDepartmentDoesNotExistMessage(depId)
            );
        }

        if (newDepartment == null) {
            throw new Service404Exception(
                Service404Exception.getDepartmentDoesNotExistMessage(newDepId)
            );
        }

        for (Employee e: department.getEmployees()) {
            e.setDepartment(newDepartment);
        }
        departmentRepository.save(department);
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

    private LocalDate stringToLocalDate(String strDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strDate, dtf);
    }

    private void setDataFromJsonToEmployee(Employee employee, String json) throws IOException {
        Map<String, Object> jsonMap = getMapFromJsonString(json);
        employee.setFirstName((String) jsonMap.get("firstName"));
        employee.setLastName((String) jsonMap.get("lastName"));
        employee.setGender(Gender.stringToEnum((String) jsonMap.get("gender")));
        employee.setPhone((String) jsonMap.get("phone"));
        employee.setEmail((String) jsonMap.get("email"));
        employee.setSalary(Double.parseDouble((String) jsonMap.get("salary")));
        employee.setBirthday(stringToLocalDate((String) jsonMap.get("birthDay")));

        employee.setChief(Boolean.parseBoolean((String) jsonMap.get("isChief")));
        Employee chief = departmentRepository.getDepartmentChief(employee.getDepartment());
        if (chief != null && employee.getChief() && employee.getId() != chief.getId()) {
            throw new Service403Exception(
                "Chief of department id=" + employee.getDepartment().getId()  + " already exist."
            );
        }

        Long positionId = Long.parseLong((String) jsonMap.get("positionId"));
        Position position = positionRepository.findOne(positionId);
        if (position == null) {
            throw new Service404Exception(
                Service404Exception.getPositionDoesNotExistMessage(positionId)
            );
        }
        employee.setPosition(position);
    }

    @Override
    public void addNewEmployeeToDepartment(long depId, String json) {
        try {
            Employee employee = new Employee();
            setDataFromJsonToEmployee(employee, json);

            Department department = departmentRepository.findOne(depId);
            if (department == null) {
                throw new Service404Exception(
                        Service404Exception.getDepartmentDoesNotExistMessage(depId)
                );
            }
            employee.setDepartment(department);

            employeeRepository.save(employee);
        } catch (IOException e) {
            throw new Service500Exception(
                Service500Exception.getFromJsonConvertingMessage(), e
            );
        }
    }

    @Override
    public void updateEmployee(long id, String json) {
        try {
            Employee employee = getEntityIfExist(id);
            setDataFromJsonToEmployee(employee, json);
            employeeRepository.save(employee);
        } catch (IOException e) {
            throw new Service500Exception(
                Service500Exception.getFromJsonConvertingMessage(), e
            );
        }
    }

//    @Override
//    public void addNewEmployeeToDepartment(long depId, Employee employee) {
//        Department department = departmentRepository.findOne(depId);
//        if (department == null) {
//            throw new Service404Exception(
//                Service404Exception.getDepartmentDoesNotExistMessage(depId)
//            );
//        }
//        employee.setDepartment(department);
//        employeeRepository.save(employee);
//    }
}
