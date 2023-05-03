package application.service;

import application.entity.Orders;

import java.util.Date;
import java.util.List;

public interface OrdersService {

    List<Orders> getAll();
    Orders findById(Long id);
    Orders findByCustomerId(Long customerId);
    Orders findByManagerId(Long managerId);
    Orders findByPickerId(Long pickerId);
    Orders findByDate(Date date);
    Orders findByStatus(String status);
}
