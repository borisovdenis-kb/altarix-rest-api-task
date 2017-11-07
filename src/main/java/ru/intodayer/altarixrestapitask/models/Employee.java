package ru.intodayer.altarixrestapitask.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.intodayer.altarixrestapitask.models.validators.implementations.ModelValidatorImpl;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee implements Serializable {

    private static final long serialVersionUID = 407876557457964591L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "last_name")
    @NotNull(message = "last_name " + ModelValidatorImpl.NOT_NULL_MSG)
    @Pattern(
        regexp = ModelValidatorImpl.CYRILLIC_REG_EXP,
        message = "last_name must match the pattern " + ModelValidatorImpl.CYRILLIC_REG_EXP
    )
    private String lastName;

    @Column(name = "first_name")
    @NotNull(message = "first_name " + ModelValidatorImpl.NOT_NULL_MSG)
    @Pattern(
        regexp = ModelValidatorImpl.CYRILLIC_REG_EXP,
        message = "first_name must match the pattern " + ModelValidatorImpl.CYRILLIC_REG_EXP
    )
    private String firstName;

    @Column(name = "middle_name")
    @Pattern(
        regexp = ModelValidatorImpl.CYRILLIC_REG_EXP,
        message = "middle_name must match the pattern " + ModelValidatorImpl.CYRILLIC_REG_EXP
    )
    private String middleName;

    @Column(name = "gender")
    @NotNull(message = "gender " + ModelValidatorImpl.NOT_NULL_MSG)
    private Gender gender;

    @Column(name = "birth_day")
    @NotNull(message = "birth_day " + ModelValidatorImpl.NOT_NULL_MSG)
    private LocalDate birthday;

    /* It is assumed that the contact phone can be a mobile phone or some internal work phone.
     * Therefore there is no regular expression for the phone format +7(XXX)XXX-XX-XX.
     */
    @Column(name = "phone")
    @NotNull(message = "phone " + ModelValidatorImpl.NOT_NULL_MSG)
    @Pattern(regexp = "[0-9+\\-() ]+", message = "phone must match the pattern [0-9+\\-() ]")
    private String phone;

    @Column(name = "email")
    @NotNull(message = "email " + ModelValidatorImpl.NOT_NULL_MSG)
    @Pattern(
        regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9]+$",
        message = "email must math the pattern ^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9]+$."
    )
    private String email;

    @Column(name = "employment_date ", nullable = false, updatable = false)
    @CreatedDate
    private LocalDate employmentDate;

    @Column(name = "dismissal_date")
    private LocalDate dismissalDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    @NotNull(message = "positionId " + ModelValidatorImpl.NOT_NULL_MSG)
    private Position position;

    @Column(name = "salary", nullable = false)
    @NotNull(message = "salary " + ModelValidatorImpl.NOT_NULL_MSG)
    private Double salary;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "is_chief", nullable = false)
    private Boolean isChief;

    public Employee() {}

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean isChief() {
        return isChief;
    }

    public void setChief(Boolean chief) {
        isChief = chief;
    }
}
