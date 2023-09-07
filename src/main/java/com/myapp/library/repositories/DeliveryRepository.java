package com.myapp.library.repositories;

import com.myapp.library.models.DeliveryInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryInformation, Integer> {

}
