package application.service;

import application.entity.Employees;

public interface EmployeesService {

    Employees findByLogin(String login);
}
