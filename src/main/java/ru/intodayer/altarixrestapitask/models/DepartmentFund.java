package ru.intodayer.altarixrestapitask.models;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "department_fund")
@EntityListeners(AuditingEntityListener.class)
public class DepartmentFund implements Serializable {

    private static final long serialVersionUID = 8413905969529953564L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private Date createDate;

    @Column(name = "fund")
    private Double departmentFund;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public DepartmentFund() {}

    public DepartmentFund(Date createDate, Double departmentFund, Department department) {
        this.departmentFund = departmentFund;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getDepartmentFund() {
        return departmentFund;
    }

    public void setDepartmentFund(Double departmentFund) {
        this.departmentFund = departmentFund;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
