package application.repository;

import application.entity.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<OrderedItems, Long> {
    OrderedItems findByOrders_OrderId(Long id);
    List<OrderedItems> findAllByOrders_OrderId(Long id);
    OrderedItems findByOrders_OrderIdAndAndItems_ItemId(Long id, Long itemId);

}
