package application.rest.controller;

import application.entity.OrderedItems;
import application.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/all")
    public @ResponseBody List<OrderedItems> getAll() {
        return orderRepository.findAll();
    }
}
