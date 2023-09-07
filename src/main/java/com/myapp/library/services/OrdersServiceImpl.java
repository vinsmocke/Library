package com.myapp.library.services;

import com.myapp.library.models.Orders;
import com.myapp.library.repositories.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService{
    private OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders save(int personId, int bookId, int deliveryId, int orderNumber) {
        Orders order = new Orders();
        order.setBookId(bookId);
        order.setDeliveryId(deliveryId);
        order.setUserId(personId);
        order.setOrderNumber(orderNumber);

        return ordersRepository.save(order);
    }
}
