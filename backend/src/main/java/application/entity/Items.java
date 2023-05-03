package application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Items implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @Column
    private String itemName;

    @Column
    private Integer count;

    @Column
    private Integer row;

    @Column
    private Integer shelf;
}
