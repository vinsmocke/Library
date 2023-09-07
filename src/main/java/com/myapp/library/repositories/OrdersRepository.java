package com.myapp.library.repositories;

import com.myapp.library.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findAllByOrderId(int id);
}
