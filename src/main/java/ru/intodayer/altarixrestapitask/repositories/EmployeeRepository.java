package ru.intodayer.altarixrestapitask.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.intodayer.altarixrestapitask.models.Employee;
import ru.intodayer.altarixrestapitask.models.Gender;
import java.time.LocalDate;
import java.util.Set;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
        value = "SELECT * FROM employee AS emp " +
                "JOIN department AS dep " +
                "ON emp.department_id = dep.id " +
                "AND dep.id = ?1 " +
                "AND emp.id = ?2 " +
                "AND emp.dismissal_date IS null", nativeQuery = true
    )
    Employee getDismissableEmployee(long departmentId, long employeeId);

    Employee findEmployeeByIdAndDepartmentId(long employeeId, long departmentId);

    Set<Employee> findEmployeeByGenderAndSalaryAndBirthday(Gender gender, Double salary, LocalDate birthDay);
}
