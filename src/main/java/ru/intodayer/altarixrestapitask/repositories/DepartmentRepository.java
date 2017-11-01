package ru.intodayer.altarixrestapitask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.Employee;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByParentDepartmentIsNull();

    Department findDepartmentByName(String name);

    @Query("select e from Employee e where e.department = :department and e.isChief = true")
    Employee getDepartmentChief(@Param("department") Department department);

}
