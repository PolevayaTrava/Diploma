package application.rest.restController;

import application.entity.Orders;
import application.repository.OrdersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class OrdersRestController {
    private final OrdersRepository ordersRepository;

    public OrdersRestController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/orders/all")
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @PutMapping("/orders/{id}")
    public Orders updateStatus(@PathVariable Long id, @RequestBody Orders orders) {
        Orders updateOrders = ordersRepository.findByOrderId(id);
        updateOrders.setOrderId(orders.getOrderId());
        updateOrders.setDate(orders.getDate());
        updateOrders.setStatus(orders.getStatus());
        updateOrders.setCustomer(orders.getCustomer());
        updateOrders.setManager(orders.getManager());
        updateOrders.setPicker(orders.getPicker());

        return ordersRepository.save(updateOrders);
    }
}
