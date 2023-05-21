package application.rest.restController;

import application.entity.Employees;
import application.repository.EmployeesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class EmployeesRestController {

    private final EmployeesRepository employeesRepository;

    public EmployeesRestController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @GetMapping("/all")
    public List<Employees> getAll() {
        return employeesRepository.findAll();
    }

    @GetMapping("/login/{login}")
    public Employees findByLogin(@PathVariable String login) {
        return employeesRepository.findByLogin(login);
    }

    @GetMapping("/fullName/{fullName}")
    public @ResponseBody Employees findByFullName(@PathVariable String fullName) {
        return employeesRepository.findByFullName(fullName);
    }
}
