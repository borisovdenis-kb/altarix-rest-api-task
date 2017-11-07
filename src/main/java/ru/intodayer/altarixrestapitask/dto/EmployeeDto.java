package ru.intodayer.altarixrestapitask.dto;

import io.swagger.annotations.ApiModelProperty;
import ru.intodayer.altarixrestapitask.models.Gender;
import ru.intodayer.altarixrestapitask.models.Position;
import java.io.Serializable;
import java.time.LocalDate;


public class EmployeeDto implements Serializable {

    private static final long serialVersionUID = 5735955930916925116L;

    @ApiModelProperty(notes = "The auto generated ID of employee entity")
    private long id;

    @ApiModelProperty(notes = "The last name of employee", required = true)
    private String lastName;

    @ApiModelProperty(notes = "The first name of employee", required = true)
    private String firstName;

    @ApiModelProperty(notes = "The middle name of employee")
    private String middleName;

    @ApiModelProperty(notes = "The gender attribute of employee", required = true)
    private Gender gender;

    @ApiModelProperty(notes = "The birth of employee", required = true)
    private LocalDate birthDay;

    @ApiModelProperty(notes = "The contact phone of employee (not necessarily mobile)", required = true)
    private String phone;

    @ApiModelProperty(notes = "The email of employee", required = true)
    private String email;

    @ApiModelProperty(notes = "The auto generated date of hiring an employee", readOnly = true)
    private LocalDate employmentDate;

    @ApiModelProperty(notes = "The auto generated date of dismissal an employee", readOnly = true)
    private LocalDate dismissalDate;

    @ApiModelProperty(notes = "Employee's position", required = true)
    private Position position;

    @ApiModelProperty(notes = "The salary of employee", required = true)
    private Double salary;

    @ApiModelProperty(notes = "A sign that the employee is the chief of the department", required = true)
    private Boolean isChief;

    @ApiModelProperty(notes = "The reference to department in which employee works", readOnly = true)
    private DepartmentDto departmentDto;

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getDismissalDate() {
        return dismissalDate;
    }

    public void setDismissalDate(LocalDate dismissalDate) {
        this.dismissalDate = dismissalDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getChief() {
        return isChief;
    }

    public void setChief(Boolean chief) {
        isChief = chief;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }
}
