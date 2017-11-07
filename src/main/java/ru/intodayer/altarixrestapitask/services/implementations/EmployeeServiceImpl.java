package ru.intodayer.altarixrestapitask.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.models.Gender;
import ru.intodayer.altarixrestapitask.models.Position;
import ru.intodayer.altarixrestapitask.models.validators.ModelValidator;
import ru.intodayer.altarixrestapitask.models.validators.implementations.ModelValidatorImpl;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.repositories.EmployeeRepository;
import ru.intodayer.altarixrestapitask.repositories.PositionRepository;
import ru.intodayer.altarixrestapitask.services.EmployeeService;
import ru.intodayer.altarixrestapitask.services.exceptions.Service400Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service404Exception;
import ru.intodayer.altarixrestapitask.services.exceptions.Service500Exception;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ModelValidator<Employee> employeeValidator;

    @Autowired
    private ModelValidator<Department> departmentValidator;

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
    public Set<Employee> getDepartmentEmployees(long depId) {
        Department department = departmentRepository.findOne(depId);
        if (department == null) {
            throw new Service404Exception(
                Service404Exception.getDepartmentDoesNotExistMessage(depId)
            );
        }
        return department.getEmployees();
    }

    @Override
    public Employee getEmployeesChief(long id) {
        Employee employee = getEntityIfExist(id);
        Employee chief = departmentRepository.getDepartmentChief(employee.getDepartment());
        if (chief == null) {
            throw new Service404Exception(
                "Chief of employee with id=" + id + " does't exist."
            );
        }
        if (chief.getId() == id) {
            throw new Service400Exception(
                "Employee with id=" + id + " is chief."
            );
        }
        return chief;
    }

    @Override
    public Set<Employee> getEmployeeByGenderSalaryBirthDay(Gender gender, double salary, String birthDay) {
        LocalDate birthDate = stringToLocalDate(birthDay);
        return employeeRepository.findEmployeeByGenderAndSalaryAndBirthday(gender, salary, birthDate);
    }

    @Override
    public void updateEmployee(long id, String json) {
        try {
            Employee employee = getEntityIfExist(id);
            setDataFromJsonToEmployee(employee, employee.getDepartment(), json);
            employeeValidator.validate(employee);
            employeeRepository.save(employee);
        } catch (IOException e) {
            throw new Service500Exception(
                Service500Exception.getFromJsonConvertingMessage(), e
            );
        }
    }

    @Override
    public void dismissEmployeeFromDepartment(long depId, long empId) {
        Employee employee = employeeRepository.getDismissableEmployee(depId, empId);

        if (employee == null) {
            throw new Service404Exception(
                "Dismissable " + Service404Exception.getEmpWorkingInDepDoesNotExistMessage(empId, depId)
            );
        }
        employee.setDepartment(null);
        employee.setDismissalDate(LocalDate.now());
        employeeValidator.validate(employee);
        employeeRepository.save(employee);
    }

    @Override
    public void transferAllEmployeesToAnotherDepartment(long depId, long newDepId) {
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
        departmentValidator.validate(department);
        departmentRepository.save(department);
    }

    @Override
    public void transferEmployeeToAnotherDepartment(long depId, long empId, long newDepId) {
        Employee employee = employeeRepository.findEmployeeByIdAndDepartmentId(empId, depId);
        Department newDepartment = departmentRepository.findOne(newDepId);

        if (employee == null) {
            throw new Service404Exception(
                Service404Exception.getEmpWorkingInDepDoesNotExistMessage(empId, depId)
            );
        }

        if (newDepartment == null) {
            throw new Service404Exception(
                Service404Exception.getDepartmentDoesNotExistMessage(newDepId)
            );
        }
        employee.setDepartment(newDepartment);
        employeeValidator.validate(employee);
        employeeRepository.save(employee);
    }

    private LocalDate stringToLocalDate(String strDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(strDate, dtf);
        } catch (DateTimeParseException e) {
            throw new Service400Exception(e.getMessage());
        }
    }

    private void setDataFromJsonToEmployee(Employee employee, Department department, String json) throws IOException {
        Map<String, Object> jsonMap = getMapFromJsonString(json);
        employee.setFirstName((String) jsonMap.get("firstName"));
        employee.setLastName((String) jsonMap.get("lastName"));
        employee.setMiddleName((String) jsonMap.get("middleName"));
        employee.setGender(Gender.stringToEnum((String) jsonMap.get("gender")));
        employee.setPhone((String) jsonMap.get("phone"));
        employee.setEmail((String) jsonMap.get("email"));

        LocalDate birthDay = ModelValidatorImpl.validateEmployeeBirthDayField(
            stringToLocalDate((String) jsonMap.get("birthDay"))
        );
        employee.setBirthday(birthDay);

        Employee chief = departmentRepository.getDepartmentChief(department);
        boolean isChief = ModelValidatorImpl.validateEmployeeIsChiefField(
            (String) jsonMap.get("isChief"), employee, chief
        );
        employee.setChief(isChief);

        Double salary = ModelValidatorImpl.validateEmployeeSalaryField(
            (String) jsonMap.get("salary"), employee, chief
        );
        employee.setSalary(salary);

        Long positionId = ModelValidatorImpl.validateLongField((String) jsonMap.get("positionId"));
        Position position = positionRepository.findOne(positionId);
        employee.setPosition(position);
    }

    @Override
    public void addNewEmployeeToDepartment(long depId, String json) {
        try {
            Employee employee = new Employee();
            Department department = departmentRepository.findOne(depId);
            if (department == null) {
                throw new Service404Exception(
                    Service404Exception.getDepartmentDoesNotExistMessage(depId)
                );
            }
            setDataFromJsonToEmployee(employee, department, json);
            employee.setDepartment(department);

            employeeValidator.validate(employee);
            employeeRepository.save(employee);
        } catch (IOException e) {
            throw new Service500Exception(
                Service500Exception.getFromJsonConvertingMessage(), e
            );
        }
    }
}
