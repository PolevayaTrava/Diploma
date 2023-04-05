package application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column
    private String fullName;

    @Column
    private String address;

    @Column
    private String phone;
}
