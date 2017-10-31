package ru.intodayer.altarixrestapitask.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.intodayer.altarixrestapitask.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByParentDepartmentIsNull();

    Department findDepartmentByName(String name);

}
