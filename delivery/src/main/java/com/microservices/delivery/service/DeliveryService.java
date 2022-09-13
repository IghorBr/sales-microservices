package com.microservices.delivery.service;

import com.microservices.delivery.domain.BaseService;
import com.microservices.delivery.dto.ProductDTO;
import com.microservices.delivery.entity.Delivery;
import com.microservices.delivery.entity.Product;

import java.util.List;
import java.util.Set;

public interface DeliveryService extends BaseService<Delivery> {

    List<Delivery> findByUser(String username);

    Delivery newDelivery(String username, Set<ProductDTO> productDTOS);
}
