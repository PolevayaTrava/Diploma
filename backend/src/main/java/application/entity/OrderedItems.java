package application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class OrderedItems implements Serializable {

    @EmbeddedId
    private OrderedItemsPK pk;

    @Column
    private Integer count;
}
