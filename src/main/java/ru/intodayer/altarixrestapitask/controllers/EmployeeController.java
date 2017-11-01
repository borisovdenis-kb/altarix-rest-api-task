package ru.intodayer.altarixrestapitask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.services.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable long id) {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(path = "/employees/{id}/chief", method = RequestMethod.GET)
    public Employee getEmployeesChief(@PathVariable long id) {
        return employeeService.getEmployeesChief(id);
    }
}
