package application.rest.controller;

import application.entity.Customer;
import application.repository.OrdersRepository;
import application.entity.Orders;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/orders")
public class OrdersController {

    private String sortDateMethod = "ASC";
    private String filterMethod = "ALL";
    private LocalDate getStartDate = LocalDate.of(2023, 01, 01);
    private LocalDate getEndDate = LocalDate.of(2030, 01, 01);
    private final OrdersRepository ordersRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/all")
    public String getAll(@ModelAttribute("customer") Customer customer, @ModelAttribute("orders2") Orders orders, Model model) {
        List<Orders> ordersList = filterAndSort();
        model.addAttribute("orders", ordersList);
        model.addAttribute("sort", sortDateMethod);
        model.addAttribute("filter", filterMethod);
        model.addAttribute("startDate", getStartDate);
        model.addAttribute("endDate", getEndDate);
        return "orders";
    }

    @GetMapping("/id/{id}")
    public String getById(@PathVariable Long id, Model model) {
        ordersRepository.findById(id).ifPresent(orders ->
                model.addAttribute("orders", orders));
        return "orders";
    }

    @GetMapping("/name/{fullName}")
    public String getByName(@PathVariable String fullName, Model model) {
        List<Orders> orders = ordersRepository.findByCustomer_FullName(fullName);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/date/{startDate}/{endDate}")
    public String findOrdersByDate(
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        getStartDate = startDate;
        getEndDate = endDate;
        return "redirect:/orders/all";
    }

    @GetMapping("/sort/{sortDate}")
    public String sortChoose(@PathVariable String sortDate) {
        sortDateMethod = sortDate;
        return "redirect:/orders/all";
    }

    @GetMapping("/filter/{filter}")
    public String filterChoose(@PathVariable String filter) {
        filterMethod = filter;
        return "redirect:/orders/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        ordersRepository.deleteById(id);
        return "redirect:/orders/all";
    }

    private List<Orders> filterAndSort() {
        List<Orders> orders = null;
        switch (filterMethod) {
            case "All":
                switch (sortDateMethod) {
                    case "ASC" -> orders = ordersRepository.findAllByOrderByDateAsc();
                    case "DESC" -> orders = ordersRepository.findAllByOrderByDateDesc();
                }
                break;
            case "Processing":
                switch (sortDateMethod) {
                    case "ASC" -> orders = ordersRepository.findByStatusOrderByDateAsc("Обработка");
                    case "DESC" -> orders = ordersRepository.findByStatusOrderByDateDesc("Обработка");
                };
                break;
            case "Assembly":
                switch (sortDateMethod) {
                    case "ASC" -> orders = ordersRepository.findByStatusOrderByDateAsc("Сборка");
                    case "DESC" -> orders = ordersRepository.findByStatusOrderByDateDesc("Сборка");
                };
                break;
            case "Ready":
                switch (sortDateMethod) {
                    case "ASC" -> orders = ordersRepository.findByStatusOrderByDateAsc("Готов");
                    case "DESC" -> orders = ordersRepository.findByStatusOrderByDateDesc("Готов");
                };
                break;
        }
        return orders;
    }

    private List<Orders> filterByDate() {
        List<Orders> orders = null;
        orders = ordersRepository.findAllByDateBetweenOrderByDateAsc(getStartDate, getEndDate);
        return orders;
    }
}
