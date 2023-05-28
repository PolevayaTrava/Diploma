package application.repository;

import application.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByOrderByDateAsc();

    List<Orders> findAllByOrderByDateDesc();

    List<Orders> findByStatusOrderByDateAsc(String status);

    List<Orders> findByStatusOrderByDateDesc(String status);

    Orders findByOrderId(Long id);
}
