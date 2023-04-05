package application.repository;

import application.entity.OrderedItemsPK;
import application.entity.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderedItems, OrderedItemsPK> {
}
