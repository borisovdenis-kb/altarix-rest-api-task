package ru.intodayer.altarixrestapitask.models;

import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "department_log")
@EntityListeners(AuditingEntityListener.class)
public class DepartmentLog implements Serializable {

    private static final long serialVersionUID = -6028509934304711439L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "logging_date", nullable = false, updatable = false)
    @CreatedDate
    private Date loggingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "operation_name", nullable = false)
    private String operationName;

    public DepartmentLog() {}

    public DepartmentLog(Date loggingDate, Department department, String operationName) {
        this.loggingDate = loggingDate;
        this.department = department;
        this.operationName = operationName;
    }

    public long getId() {
        return id;
    }

    public Date getLoggingDate() {
        return loggingDate;
    }

    public void setLoggingDate(Date loggingDate) {
        this.loggingDate = loggingDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
