package application.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Data
public class OrderedItems implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Items items;

    @Column
    private Integer count;
}
