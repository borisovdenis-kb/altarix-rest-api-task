package ru.intodayer.altarixrestapitask.dto.implementations;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ru.intodayer.altarixrestapitask.dto.DepartmentDto;
import ru.intodayer.altarixrestapitask.dto.DtoConverter;
import ru.intodayer.altarixrestapitask.dto.EmployeeDto;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;


@Component
public class DtoConverterImpl implements DtoConverter {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto convertEmployeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setMiddleName(employee.getMiddleName());
        employeeDto.setGender(employee.getGender());
        employeeDto.setBirthDay(employee.getBirthday());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setEmploymentDate(employee.getEmploymentDate());
        employeeDto.setDismissalDate(employee.getDismissalDate());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setChief(employee.isChief());
        employeeDto.setDepartmentDto(convertDepartmentToDto(employee.getDepartment()));
        return employeeDto;
    }

    @Override
    public DepartmentDto convertDepartmentToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(department.getName());
        departmentDto.setCreateDate(department.getCreateDate());
        departmentDto.setDepartmentChief(departmentRepository.getDepartmentChief(department));
        departmentDto.setEmployeeAmount(department.getEmployees().size());
        return departmentDto;
    }
}
