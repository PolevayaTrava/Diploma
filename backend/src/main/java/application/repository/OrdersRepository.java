package application.repository;

import application.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByDateBetween(Date startDate, Date endDate);
    List<Orders> findAllByDateBetweenOrderByDateAsc(Date startDate, Date endDate);
    //List<Orders> findOrdersByCustomer_FullName(String fullName);
    List<Orders> findByStatusOrderByDateAsc(String status);
    List<Orders> findAllByOrderByDateAsc();
    List<Orders> findAllByOrderByDateDesc();
    List<Orders> findByStatusOrderByDateDesc(String status);
    List<Orders> findByCustomer_FullName(String fullName);
    List<Orders> findByCustomer_FullNameOrderByDateAsc(String fullName);
    List<Orders> findByCustomer_FullNameOrderByDateDesc(String fullName);
}
