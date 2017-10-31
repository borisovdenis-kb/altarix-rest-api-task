package ru.intodayer.altarixrestapitask.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intodayer.altarixrestapitask.models.Department;


@Repository
public interface DepartmentRepositry extends JpaRepository<Department, Long> {

}
