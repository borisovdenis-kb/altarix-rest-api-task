package ru.intodayer.altarixrestapitask.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import java.io.Serializable;
import java.time.LocalDate;


public class DepartmentDto implements Serializable {

    private static final long serialVersionUID = 3652207543819455311L;

    @Autowired
    private static ModelMapper modelMapper;

    @Autowired
    private static DepartmentRepository departmentRepository;

    @ApiModelProperty(notes = "The auto generated ID of department entity")
    private long id;

    @ApiModelProperty(notes = "The name of department", required = true)
    private String name;

    @ApiModelProperty(notes = "The auto generated date of creation of department entity")
    private LocalDate createDate;

    @ApiModelProperty(notes = "The chief of the department")
    private Employee departmentChief;

    @ApiModelProperty(notes = "Amount of employees working in department")
    private int employeeAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Employee getDepartmentChief() {
        return departmentChief;
    }

    public void setDepartmentChief(Employee departmentChief) {
        this.departmentChief = departmentChief;
    }

    public int getEmployeeAmount() {
        return employeeAmount;
    }

    public void setEmployeeAmount(int employeeAmount) {
        this.employeeAmount = employeeAmount;
    }
}
