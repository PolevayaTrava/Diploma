package application.security;

import application.entity.Employees;
import application.entity.Role;
import application.repository.EmployeesRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class EmployeesDetailsService implements UserDetailsService {

    private final EmployeesRepository employeesRepository;

    public EmployeesDetailsService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employees employees = employeesRepository.findByLogin(login);

        if (employees != null) {
            return new org.springframework.security.core.userdetails.User(
                    employees.getLogin(),
                    employees.getPassword(),
                    mapRolesToAuthorities(employees.getRoles()));
        }
        else {
           throw new UsernameNotFoundException("Пользователь не найден");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
