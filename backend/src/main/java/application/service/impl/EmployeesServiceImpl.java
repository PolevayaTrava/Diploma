package application.service.impl;

import application.entity.Employees;
import application.repository.EmployeesRepository;
import application.repository.RoleRepository;
import application.service.EmployeesService;
import org.springframework.stereotype.Service;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    private EmployeesRepository employeesRepository;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Employees findByLogin(String login) {
        return employeesRepository.findByLogin(login);
    }
}
