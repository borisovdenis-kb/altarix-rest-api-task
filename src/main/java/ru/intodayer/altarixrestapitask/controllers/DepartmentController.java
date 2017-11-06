package ru.intodayer.altarixrestapitask.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.services.DepartmentService;
import ru.intodayer.altarixrestapitask.swagger.DepartmentDocDescription;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = DepartmentDocDescription.ADD_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments", method = RequestMethod.POST)
    public void addDepartment(@RequestBody String json) {
        departmentService.addDepartment(json);
    }

    @ApiOperation(value = DepartmentDocDescription.UPDATE_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{id}", method = RequestMethod.PUT)
    public void updateDepartment(@PathVariable long id, @RequestBody Department department) {
        departmentService.updateDepartmentName(id, department);
    }

    @ApiOperation(value = DepartmentDocDescription.CHANGE_PARENT_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{depId}/newparentdepartment/{newParentDepId}", method = RequestMethod.PUT)
    public void changeParentDepartment(@PathVariable long depId, @PathVariable long newParentDepId) {
        departmentService.changeParentDepartment(depId, newParentDepId);
    }

    @ApiOperation(value = DepartmentDocDescription.DELETE_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{id}", method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }

    @ApiOperation(value = DepartmentDocDescription.GET_DEPARTMENT_DESC)
    @RequestMapping(
        path = "/departments/{id}",
        method = RequestMethod.GET,
        produces = {"application/json"}
    )
    public String getDepartment(@PathVariable long id) {
        return departmentService.getDepartment(id);
    }

    @ApiOperation(value = DepartmentDocDescription.GET_DEPARTMENT_BY_NAME_DESC)
    @RequestMapping(path = "/departments", method = RequestMethod.GET)
    public Department getDepartmentByName(@RequestParam String name) {
        return departmentService.getDepartmentByName(name);
    }

    @ApiOperation(value = DepartmentDocDescription.GET_ALL_SUB_DEPARTMENTS_DESC)
    @RequestMapping(path = "/departments/{id}/subdepartments", method = RequestMethod.GET)
    public Set<Department> getAllSubDepartments(@PathVariable long id) {
        return departmentService.getAllSubDepartments(id);
    }

    @ApiOperation(value = DepartmentDocDescription.GET_CHILD_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{id}/childdepartments", method = RequestMethod.GET)
    public Set<Department> getChildDepartments(@PathVariable long id) {
        return departmentService.getChildDepartments(id);
    }

    @ApiOperation(value = DepartmentDocDescription.GET_PARENT_DEPARTMENTS_DESC)
    @RequestMapping(path = "/departments/{id}/parentDepartments", method = RequestMethod.GET)
    public Set<Department> getParentDepartments(@PathVariable long id) {
        return departmentService.getParentDepartments(id);
    }

    @ApiOperation(value = DepartmentDocDescription.GET_DEPARTMENT_SALARY_FUND_DESC)
    @RequestMapping(
        path = "/departments/{id}/fund",
        method = RequestMethod.GET,
        produces = {"application/json"}
    )
    public String getDepartmentSalaryFund(@PathVariable long id) {
        return departmentService.getDepartmentSalaryFund(id);
    }
}
