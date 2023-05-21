package application.rest.controller;

import application.entity.OrderedItems;
import application.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        OrderedItems order = orderRepository.findByOrders_OrderId(id);
        model.addAttribute("order", order);
        return "order";
    }
}
