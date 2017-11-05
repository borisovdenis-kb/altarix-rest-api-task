package ru.intodayer.altarixrestapitask.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department d where d.parentDepartment is null")
    Department getRootDepartment();

    @Query("select e from Employee e where e.department = :department and e.isChief = true")
    Employee getDepartmentChief(@Param("department") Department department);

    @Query("select sum(e.salary) from Employee e where e.department = :department")
    Double getDepartmentFund(@Param("department") Department department);

    Department findDepartmentByName(String name);

}
