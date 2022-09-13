package com.microservices.delivery.controller;

import com.microservices.delivery.domain.BaseController;
import com.microservices.delivery.dto.DeliveryDTO;
import com.microservices.delivery.entity.Delivery;
import com.microservices.delivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deliveries")
@Tag(name = "Delivery Controller", description = "Responsável por Criar e consultar entregas")
public class DeliveryController extends BaseController<Delivery, DeliveryDTO> {

    private final DeliveryService deliveryService;

    private final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public DeliveryController(DeliveryService deliveryService) {
        super(Delivery.class, DeliveryDTO.class, deliveryService);
        this.deliveryService = deliveryService;
    }

    @GetMapping("/user/{username}")
    @Operation(description = "Lista todas as compras de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public ResponseEntity<List<DeliveryDTO>> findByUser(@PathVariable(value = "username", required = true) String username) {
        LOGGER.info("Buscando todas as entregas de um usuário");

        List<Delivery> deliveries = deliveryService.findByUser(username);

        List<DeliveryDTO> deliveryDTOS = mapList(deliveries);

        return ResponseEntity.ok(deliveryDTOS);
    }
}
