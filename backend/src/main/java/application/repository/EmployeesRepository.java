package application.repository;

import application.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {

    Employees findByLogin(String login);
    Employees findByFullName(String fullName);
}
