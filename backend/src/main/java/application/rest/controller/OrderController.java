package application.rest.controller;

import application.PDFExporter;
import application.entity.OrderedItems;
import application.repository.OrderRepository;
import application.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderServiceImpl orderService;
    public OrderController(OrderRepository orderRepository, OrderServiceImpl orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public @ResponseBody List<OrderedItems> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        List<OrderedItems> order = orderRepository.findAllByOrders_OrderId(id);
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/printOrder/{id}")
    public void printOrder(@PathVariable Long id, HttpServletResponse response) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Zakaz_" + id + "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<OrderedItems> order = orderService.findAllById(id);

        PDFExporter exporter = new PDFExporter(order);
        exporter.export(response, id);
    }
}
