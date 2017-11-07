package ru.intodayer.altarixrestapitask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.intodayer.altarixrestapitask.models.DepartmentLog;


public interface DepartmentLogRepository extends JpaRepository<DepartmentLog, Long> {

}
