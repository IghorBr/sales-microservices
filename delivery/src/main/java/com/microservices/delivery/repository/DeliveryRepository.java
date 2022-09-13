package com.microservices.delivery.repository;

import com.microservices.delivery.domain.BaseRepository;
import com.microservices.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends BaseRepository<Delivery> {

    @Query(value = "FROM Delivery d INNER JOIN d.user u WHERE u.username = ?1")
    List<Delivery> findByUser(String username);
}
