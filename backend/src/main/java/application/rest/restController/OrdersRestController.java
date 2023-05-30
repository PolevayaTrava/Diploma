package application.rest.restController;

import application.entity.Orders;
import application.repository.OrdersRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

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

        if (orders.getStatus().equals("Готов")) {
            sendEmail(id);
        }

        return ordersRepository.save(updateOrders);
    }

    public void sendEmail(Long id) {
        Orders orders = ordersRepository.findByOrderId(id);
        String to = orders.getCustomer().getEmail();
        String from = "email";
        String address = orders.getCustomer().getAddress();
        String fullName = orders.getCustomer().getFullName();
        String orderId = orders.getOrderId().toString();

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.mail.ru");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.user", "email");
        properties.put("mail.password", "password");


        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email", "password");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Заказ №" + orderId);

            message.setText("Здравствуйте " + fullName +
                    ", ваш заказ готов и будет отправлен по адресу " + address + " в ближайшее время");

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
