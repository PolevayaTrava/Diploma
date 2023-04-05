package application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Employees manager;

    @ManyToOne(fetch = FetchType.EAGER)
    private Employees picker;

    @Column
    private Date date;

    @Column
    private String status;

    @OneToMany(mappedBy = "pk.orders",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OrderedItems> orderedItems;
}
