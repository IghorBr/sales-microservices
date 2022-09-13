package com.microservices.sales.controller;

import com.microservices.sales.domain.BaseController;
import com.microservices.sales.dto.NewPurchaseDTO;
import com.microservices.sales.dto.PurchaseDTO;
import com.microservices.sales.entity.Purchase;
import com.microservices.sales.exception.ObjectNotFoundException;
import com.microservices.sales.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase Controller", description = "Responsável por Compras")
public class PurchaseController extends BaseController<Purchase, PurchaseDTO> {

    private final PurchaseService purchaseService;

    private final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public PurchaseController(PurchaseService purchaseService) {
        super(Purchase.class, PurchaseDTO.class, purchaseService);
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @Operation(description = "Cria uma nova compra para um determinado usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Object not found")
    })
    public ResponseEntity<Void> createPurchase(@RequestBody NewPurchaseDTO dto) throws ObjectNotFoundException {
        LOGGER.info("Criando nova compra");

        purchaseService.createPurchase(dto);

        return ResponseEntity.noContent().build();
    }
}
