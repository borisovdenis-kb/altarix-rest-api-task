package ru.intodayer.altarixrestapitask.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.models.Gender;
import ru.intodayer.altarixrestapitask.services.EmployeeService;
import ru.intodayer.altarixrestapitask.swagger.EmployeeDoc;

import java.util.Set;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(EmployeeDoc.GET_EMPLOYEE_BY_ATTRIBUTES_DESC)
    @RequestMapping(path = "/employees", params = {"gender", "salary", "birthDay"}, method = RequestMethod.GET)
    public Set<Employee> getEmployeeByGenderSalaryBirthDay(@RequestParam Gender gender,
                                                           @RequestParam double salary,
                                                           @RequestParam String birthDay) {
        return employeeService.getEmployeeByGenderSalaryBirthDay(gender, salary, birthDay);
    }

    @ApiOperation(EmployeeDoc.GET_EMPLOYEE_DESC)
    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable long id) {
        return employeeService.getEmployee(id);
    }

    @ApiOperation(EmployeeDoc.GET_DEPARTMENT_EMPLOYEES_DESC)
    @RequestMapping(path = "/departments/{depId}/employees", method = RequestMethod.GET)
    public Set<Employee> getDepartmentEmployees(@PathVariable long depId) {
        return employeeService.getDepartmentEmployees(depId);
    }

    @ApiOperation(EmployeeDoc.UPDATE_EMPLOYEE_DESC)
    @RequestMapping(path = "/employees/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable long id, @RequestBody String json) {
        employeeService.updateEmployee(id, json);
    }

    @ApiOperation(EmployeeDoc.GET_EMPLOYEE_CHIEF_DESC)
    @RequestMapping(path = "/employees/{id}/chief", method = RequestMethod.GET)
    public Employee getEmployeesChief(@PathVariable long id) {
        return employeeService.getEmployeesChief(id);
    }

    @ApiOperation(EmployeeDoc.ADD_NEW_EMPLOYEE_TO_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{depId}/employees", method = RequestMethod.POST)
    public void addNewEmployeeToDepartment(@PathVariable long depId, @RequestBody String json) {
        employeeService.addNewEmployeeToDepartment(depId, json);
    }

    @ApiOperation(EmployeeDoc.DISMISS_EMPLOYEE_FROM_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{depId}/employees/{empId}", method = RequestMethod.DELETE)
    public void dismissEmployeeFromDepartment(@PathVariable long depId, @PathVariable long empId) {
        employeeService.dismissEmployeeFromDepartment(depId, empId);
    }

    @ApiOperation(EmployeeDoc.TRANSFER_EMPLOYEES_TO_ANOTHER_DEPARTMENT_DESC)
    @RequestMapping(
        path = "/departments/{depId}/employees/{empId}/newdepartment/{newDepId}",
        method = RequestMethod.PUT
    )
    public void transferEmployeeToAnotherDepartment(@PathVariable long depId,
                                                    @PathVariable long empId,
                                                    @PathVariable long newDepId) {
        employeeService.transferEmployeeToAnotherDepartment(depId, empId, newDepId);
    }

    @ApiOperation(EmployeeDoc.TRANSFER_ALL_EMPLOYEES_TO_ANOTHER_DEPARTMENT_DESC)
    @RequestMapping(
        path = "/departments/{depId}/employees/newdepartment/{newDepId}",
        method = RequestMethod.PUT
    )
    public void transferAllEmployeesToAnotherDepartment(@PathVariable long depId, @PathVariable long newDepId) {
        employeeService.transferAllEmployeesToAnotherDepartment(depId, newDepId);
    }
}
