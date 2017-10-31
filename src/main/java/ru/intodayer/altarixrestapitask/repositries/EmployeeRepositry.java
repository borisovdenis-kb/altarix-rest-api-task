package ru.intodayer.altarixrestapitask.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intodayer.altarixrestapitask.models.Employee;


@Repository
public interface EmployeeRepositry extends JpaRepository<Employee, Long> {

}
