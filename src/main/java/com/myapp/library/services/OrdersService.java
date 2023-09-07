package com.myapp.library.services;

import com.myapp.library.models.Orders;

public interface OrdersService {
    Orders save(int personId, int bookId, int deliveryId, int orderNumber);
}
