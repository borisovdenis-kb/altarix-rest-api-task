package ru.intodayer.altarixrestapitask.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.intodayer.altarixrestapitask.models.Department;
import ru.intodayer.altarixrestapitask.models.DepartmentFund;
import ru.intodayer.altarixrestapitask.repositories.DepartmentFundRepository;
import ru.intodayer.altarixrestapitask.repositories.DepartmentRepository;
import ru.intodayer.altarixrestapitask.services.DepartmentUtilityService;


@Service
//@EnableScheduling
public class DepartmentUtilityServiceImpl implements DepartmentUtilityService {

    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentFundRepository departmentFundRepository;

    @Scheduled(fixedRate = MINUTE * 5)
    @Override
    public void saveDepartmentFundHistory() {
        for (Department department: departmentRepository.findAll()) {
            DepartmentFund departmentFund = new DepartmentFund();
            departmentFund.setDepartment(department);
            departmentFund.setDepartmentFund(departmentRepository.getDepartmentFund(department));
            departmentFundRepository.save(departmentFund);
        }
    }
}
