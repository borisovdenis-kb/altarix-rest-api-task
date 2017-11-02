package ru.intodayer.altarixrestapitask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.services.DepartmentService;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(path = "/departments", method = RequestMethod.POST)
    public void addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
    }

    @RequestMapping(path = "/departments/{id}", method = RequestMethod.PUT)
    public void updateDepartment(@PathVariable long id, @RequestBody Department department) {
        departmentService.updateDepartmentName(id, department);
    }

    @RequestMapping(path = "/departments/{id}", method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }

    @RequestMapping(
        path = "/departments/{id}",
        method = RequestMethod.GET,
        produces = {"application/json"}
    )
    public String getDepartment(@PathVariable long id) {
        return departmentService.getDepartment(id);
    }

    @RequestMapping(path = "/departments", method = RequestMethod.GET)
    public Department getDepartmentByName(@RequestParam String name) {
        return departmentService.getDepartmentByName(name);
    }

    @RequestMapping(path = "/departments/{id}/subdepartments", method = RequestMethod.GET)
    public Set<Department> getSubdepartments(@PathVariable long id) {
        return departmentService.getSubDepartments(id);
    }

    @RequestMapping(path = "/departments/{id}/subdepartments", params = {"level"}, method = RequestMethod.GET)
    public Set<Department> getSubDepartments(@PathVariable long id, @RequestParam int level) {
        return departmentService.getSubDepartments(id, level);
    }

    @RequestMapping(path = "/departments/{id}/parentDepartments", method = RequestMethod.GET)
    public Set<Department> getParentDepartments(@PathVariable long id) {
        return departmentService.getParentDepartments(id);
    }

    @RequestMapping(
        path = "/departments/{id}/fund",
        method = RequestMethod.GET,
        produces = {"application/json"}
    )
    public String getDepartmentFund(@PathVariable long id) {
        return departmentService.getDepartmentFund(id);
    }

    @RequestMapping(path = "/departments/{id}/employees", method = RequestMethod.GET)
    public Set<Employee> getDepartmentEmployees(@PathVariable long id) {
        return departmentService.getDepartmentEmployees(id);
    }
}
