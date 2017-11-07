package ru.intodayer.altarixrestapitask.log;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.DepartmentLog;
import ru.intodayer.altarixrestapitask.repositories.DepartmentLogRepository;
import java.util.Date;


@Component
@Aspect
public class LoggingAspect {

    @Autowired
    private DepartmentLogRepository departmentLogRepository;

    @AfterReturning(
        pointcut = "execution(* ru.intodayer.altarixrestapitask" +
            ".services.implementations.DepartmentServiceImpl.addDepartment(..))",
        returning = "val"
    )
    public void logDepartmentCreation(Object val) {
        DepartmentLog departmentLog = new DepartmentLog(
            new Date(),
            (Department) val,
            "create new department"
        );
        departmentLogRepository.save(departmentLog);
    }

    @AfterReturning(
        pointcut = "execution(* ru.intodayer.altarixrestapitask" +
            ".services.DepartmentService.updateDepartmentName(..))",
        returning = "val"
    )
    public void logDepartmentRenaming(Object val) {
        DepartmentLog departmentLog = new DepartmentLog(
            new Date(),
            (Department) val,
            "update department name"
        );
        departmentLogRepository.save(departmentLog);
    }

    @AfterReturning(
        pointcut = "execution(* ru.intodayer.altarixrestapitask" +
            ".services.DepartmentService.changeParentDepartment(..))",
        returning = "val"
    )
    public void logParentDepartmentChanging(Object val) {
        DepartmentLog departmentLog = new DepartmentLog(
            new Date(),
            (Department) val,
            "change parent department of department"
        );
        departmentLogRepository.save(departmentLog);
    }
}
