package ru.intodayer.altarixrestapitask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.services.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(path = "/departments/{depId}/employees", method = RequestMethod.POST)
    public void addNewEmployeeToDepartment(@PathVariable long depId, @RequestBody String json) {
        employeeService.addNewEmployeeToDepartment(depId, json);
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable long id) {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(path = "/employees/{id}/chief", method = RequestMethod.GET)
    public Employee getEmployeesChief(@PathVariable long id) {
        return employeeService.getEmployeesChief(id);
    }

    @RequestMapping(path = "/departments/{depId}/employees/{empId}", method = RequestMethod.DELETE)
    public void deleteEmployeeFromDepartment(@PathVariable long depId, @PathVariable long empId) {
        employeeService.deleteEmployeeFromDepartment(depId, empId);
    }

    @RequestMapping(
        path = "/departments/{depId}/employees/{empId}/newdepartment/{newDepId}",
        method = RequestMethod.PUT
    )
    public void changeEmployeesDepartment(@PathVariable long depId,
                                          @PathVariable long empId,
                                          @PathVariable long newDepId) {
        employeeService.changeEmployeesDepartment(depId, empId, newDepId);
    }

    @RequestMapping(
            path = "/departments/{depId}/employees/newdepartment/{newDepId}",
            method = RequestMethod.PUT
    )
    public void changeEmployeesDepartment(@PathVariable long depId, @PathVariable long newDepId) {
        employeeService.changeEmployeesDepartment(depId, newDepId);
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable long id, @RequestBody String json) {
        employeeService.updateEmployee(id, json);
    }
}
