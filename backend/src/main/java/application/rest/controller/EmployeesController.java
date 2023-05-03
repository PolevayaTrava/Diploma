package application.rest.controller;

import application.entity.Employees;
import application.entity.Role;
import application.repository.EmployeesRepository;
import application.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class EmployeesController {

    private final EmployeesRepository employeesRepository;

    private final RoleRepository roleRepository;

    public EmployeesController(EmployeesRepository employeesRepository, RoleRepository roleRepository) {
        this.employeesRepository = employeesRepository;
        this.roleRepository = roleRepository;
    }

    /*@PostMapping(path = "/add")
    public @ResponseBody Employees addUser (
            @RequestParam String fullName,
            String login, String password) {
        Employees employees = new Employees();
        employees.setFullName(fullName);
        employees.setLogin(login);
        employees.setPassword(password);
        return employeesRepository.save(employees);
    }*/

    @GetMapping("/all")
    public @ResponseBody List<Employees> getAll() {
        return employeesRepository.findAll();
    }

    @GetMapping("/{fullName}")
    public @ResponseBody Employees findByFullName(@PathVariable String fullName) {
        return employeesRepository.findByFullName(fullName);
    }
}
