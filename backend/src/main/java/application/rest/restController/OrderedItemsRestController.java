package application.rest.restController;

import application.entity.OrderedItems;
import application.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/order")
public class OrderedItemsRestController {

    private final OrderRepository orderRepository;

    public OrderedItemsRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public List<OrderedItems> findById(@PathVariable Long id) {
        return orderRepository.findAllByOrders_OrderId(id);
    }

    @PutMapping("/{id}/{itemId}")
    public OrderedItems updateCount(@PathVariable Long id, @PathVariable Long itemId, @RequestBody OrderedItems orderedItems) {
        OrderedItems updateOrderedItems = orderRepository.findByOrders_OrderIdAndAndItems_ItemId(id, itemId);
        updateOrderedItems.setId(orderedItems.getId());
        updateOrderedItems.setOrders(orderedItems.getOrders());
        updateOrderedItems.setCount(orderedItems.getCount());
        updateOrderedItems.setItems(orderedItems.getItems());
        updateOrderedItems.setCountFact(orderedItems.getCountFact());

        return orderRepository.save(updateOrderedItems);
    }
}
