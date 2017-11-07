package ru.intodayer.altarixrestapitask.models;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.intodayer.altarixrestapitask.models.validators.implementations.ModelValidatorImpl;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "department")
@EntityListeners(AuditingEntityListener.class)
public class Department implements Serializable {

    private static final long serialVersionUID = 5203441378638064229L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotNull(message = "name " + ModelValidatorImpl.NOT_NULL_MSG)
    private String name;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createDate;

    @JsonIgnore
    @OneToMany(mappedBy = "parentDepartment", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Department> childDepartments;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<DepartmentFund> fundHistory;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<DepartmentLog> departmentLogs;

    protected Department() {}

    public Department(String name, LocalDate createDate, Department parentDepartment) {
        this.name = name;
        this.createDate = createDate;
        this.parentDepartment = parentDepartment;
    }

    public long getId() {
        return id;
    }

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

    public Set<Department> getChildDepartments() {
        return childDepartments;
    }

    public void setChildDepartments(Set<Department> childDepartments) {
        this.childDepartments = childDepartments;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
