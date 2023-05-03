package application.rest.dto;

import lombok.Data;

@Data
public class EmployeesDTO {
    private Long employeeId;
    private String fullName;
    private String login;
    private String password;
}
