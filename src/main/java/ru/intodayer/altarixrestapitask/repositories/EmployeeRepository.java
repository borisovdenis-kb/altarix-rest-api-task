package ru.intodayer.altarixrestapitask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.intodayer.altarixrestapitask.models.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
