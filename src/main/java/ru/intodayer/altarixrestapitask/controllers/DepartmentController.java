package ru.intodayer.altarixrestapitask.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.intodayer.altarixrestapitask.dto.DepartmentDto;
import ru.intodayer.altarixrestapitask.dto.DtoConverter;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.services.DepartmentService;
import ru.intodayer.altarixrestapitask.swagger.DepartmentDoc;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DtoConverter dtoConverter;

    @ApiOperation(DepartmentDoc.ADD_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments", method = RequestMethod.POST)
    public void addDepartment(@RequestBody String json) {
        departmentService.addDepartment(json);
    }

    @ApiOperation(DepartmentDoc.UPDATE_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{id}", method = RequestMethod.PUT)
    public void updateDepartment(@PathVariable long id, @RequestBody Department department) {
        departmentService.updateDepartmentName(id, department);
    }

    @ApiOperation(DepartmentDoc.CHANGE_PARENT_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{depId}/newparentdepartment/{newParentDepId}", method = RequestMethod.PUT)
    public void changeParentDepartment(@PathVariable long depId, @PathVariable long newParentDepId) {
        departmentService.changeParentDepartment(depId, newParentDepId);
    }

    @ApiOperation(DepartmentDoc.DELETE_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{id}", method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }

    @ApiOperation(DepartmentDoc.GET_DEPARTMENT_DESC)
    @RequestMapping(
        path = "/departments/{id}",
        method = RequestMethod.GET,
        produces = {"application/json"}
    )
    public DepartmentDto getDepartment(@PathVariable long id) {
        return dtoConverter.convertDepartmentToDto(departmentService.getDepartment(id));
    }

    @ApiOperation(DepartmentDoc.GET_DEPARTMENT_BY_NAME_DESC)
    @RequestMapping(path = "/departments", method = RequestMethod.GET)
    public DepartmentDto getDepartmentByName(@RequestParam String name) {
        return dtoConverter.convertDepartmentToDto(departmentService.getDepartmentByName(name));
    }

    @ApiOperation(DepartmentDoc.GET_ALL_SUB_DEPARTMENTS_DESC)
    @RequestMapping(path = "/departments/{id}/subdepartments", method = RequestMethod.GET)
    public Set<DepartmentDto> getAllSubDepartments(@PathVariable long id) {
        return departmentService.getAllSubDepartments(id)
            .stream()
            .map((d) -> dtoConverter.convertDepartmentToDto(d))
            .collect(Collectors.toSet());
    }

    @ApiOperation(DepartmentDoc.GET_CHILD_DEPARTMENT_DESC)
    @RequestMapping(path = "/departments/{id}/childdepartments", method = RequestMethod.GET)
    public Set<DepartmentDto> getChildDepartments(@PathVariable long id) {
        return departmentService.getChildDepartments(id)
            .stream()
            .map((d) -> dtoConverter.convertDepartmentToDto(d))
            .collect(Collectors.toSet());
    }

    @ApiOperation(DepartmentDoc.GET_PARENT_DEPARTMENTS_DESC)
    @RequestMapping(path = "/departments/{id}/parentDepartments", method = RequestMethod.GET)
    public Set<DepartmentDto> getParentDepartments(@PathVariable long id) {
        return departmentService.getParentDepartments(id)
            .stream()
            .map((d) -> dtoConverter.convertDepartmentToDto(d))
            .collect(Collectors.toSet());
    }

    @ApiOperation(DepartmentDoc.GET_DEPARTMENT_SALARY_FUND_DESC)
    @RequestMapping(
        path = "/departments/{id}/fund",
        method = RequestMethod.GET,
        produces = {"application/json"}
    )
    public String getDepartmentSalaryFund(@PathVariable long id) {
        return departmentService.getDepartmentSalaryFund(id);
    }
}
