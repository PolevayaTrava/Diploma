package application.rest.restController;

import application.entity.Customer;
import application.entity.OrderedItems;
import application.repository.OrderRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

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

        if (updateOrderedItems.getOrders().getStatus().equals("Готов")) {
            sendEmail(updateOrderedItems.getOrders().getCustomer(), id);
        }

        return orderRepository.save(updateOrderedItems);
    }

    public void sendEmail(Customer customer, Long id) {
        String fullName = customer.getFullName();
        String address = customer.getAddress();
        String getEmail = customer.getEmail();
        String host = "localhost";
        String sendEmail = "a.kanev422@gmail.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        String sendText = "Здравуствуйте " + fullName + ".\nВаш заказ " +
                id + " готов и отправляется по адресу " + address;
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getEmail));
            message.setSubject("Заказ № " + id);
            message.setText(sendText);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
