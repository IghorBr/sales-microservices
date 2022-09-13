package com.microservices.delivery.service.impl;

import com.microservices.delivery.domain.BaseServiceImpl;
import com.microservices.delivery.dto.ProductDTO;
import com.microservices.delivery.entity.Delivery;
import com.microservices.delivery.entity.Product;
import com.microservices.delivery.entity.User;
import com.microservices.delivery.exception.ObjectNotFoundException;
import com.microservices.delivery.repository.DeliveryRepository;
import com.microservices.delivery.service.DeliveryService;
import com.microservices.delivery.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class DeliveryServiceImpl extends BaseServiceImpl<Delivery> implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, UserService userService) {
        super(deliveryRepository);
        this.deliveryRepository = deliveryRepository;
        this.userService = userService;
    }

    @Override
    public List<Delivery> findByUser(String username) {
        return deliveryRepository.findByUser(username);
    }

    @Override
    public Delivery newDelivery(String username, Set<ProductDTO> productDTOS) {
        LOGGER.info("Nova entrega recebida");

        Delivery delivery = new Delivery();

        User user = userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        delivery.setUser(user);

        for (ProductDTO dto : productDTOS) {
            Product p = new Product(dto.getName(), dto.getDescription(), dto.getQuantity(), dto.getPrice());

            delivery.addProducts(p);
        }

        return this.save(delivery);
    }
}
