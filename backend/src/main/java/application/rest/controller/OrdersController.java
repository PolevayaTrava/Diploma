package application.rest.controller;

import application.repository.OrdersRepository;
import application.entity.Orders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/all")
    public @ResponseBody List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        ordersRepository.deleteById(id);
    }
}
