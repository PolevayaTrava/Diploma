package application.service.impl;

import application.entity.OrderedItems;
import application.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderedItems> findAllById(Long id) {
        return orderRepository.findAllByOrders_OrderId(id);
    }
}
