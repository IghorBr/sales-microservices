package com.microservices.sales.service.impl;

import com.microservices.sales.domain.BaseServiceImpl;
import com.microservices.sales.dto.*;
import com.microservices.sales.entity.Product;
import com.microservices.sales.entity.Purchase;
import com.microservices.sales.entity.PurchaseProduct;
import com.microservices.sales.event.EventDispatcher;
import com.microservices.sales.event.NewBuyEvent;
import com.microservices.sales.exception.ObjectNotFoundException;
import com.microservices.sales.repository.PurchaseRepository;
import com.microservices.sales.service.ProductService;
import com.microservices.sales.service.PurchaseService;
import com.microservices.sales.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PurchaseServiceImpl extends BaseServiceImpl<Purchase> implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;
    private final UserService userService;

    private final RestTemplate restTemplate;

    private final EventDispatcher eventDispatcher;

    private final String BANK_API_URL = "http://localhost:8000/api/bank";

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductService productService, UserService userService, RestTemplate restTemplate, EventDispatcher eventDispatcher) {
        super(purchaseRepository);
        this.purchaseRepository = purchaseRepository;
        this.productService = productService;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    @Transactional
    public void createPurchase(NewPurchaseDTO dto) {
        Purchase newPurchase = new Purchase(userService.findById(2L).orElseThrow(() -> new ObjectNotFoundException("Not Found")));

        List<Product> products = new ArrayList<>();

        newPurchase.setDiscount(dto.getDiscount());

        for (ProductQuantityDTO i : dto.getItens()) {
            Product product = productService.findById(i.getProductId()).orElseThrow(() -> new ObjectNotFoundException("Not Found!"));

            newPurchase.addProduct(
                    product,
                    i.getQuantity()
            );

            products.add(product);
        }

        this.verifyPurchase(newPurchase);

        productService.saveAll(products);
        newPurchase = purchaseRepository.save(newPurchase);

        sendEvent(newPurchase);
    }

    private void sendEvent(Purchase purchase) {
        Set<ProductDTO> productDTOS = new HashSet<>();
        ModelMapper mapper = new ModelMapper();

        for (PurchaseProduct item : purchase.getItens()) {
            ProductDTO dto = mapper.map(item.getProduct(), ProductDTO.class);
            dto.setQuantity(item.getQuantity());

            productDTOS.add( dto );
        }

        AddressDTO addressDTO = mapper.map(purchase.getUser().getAddress(), AddressDTO.class);

        NewBuyEvent event = new NewBuyEvent(purchase.getUser().getUsername(), productDTOS);

        eventDispatcher.send(event);
    }

    private boolean verifyPurchase(Purchase purchase) {

        NewBuyDTO newBuyDTO = new NewBuyDTO(purchase.getUser().getUsername(), purchase.getUser().getPassword(), BigDecimal.valueOf(purchase.getTotal()));

        HttpEntity<NewBuyDTO> httpEntity = new HttpEntity<>(newBuyDTO);

        ResponseEntity<Boolean> booleanResponseEntity = restTemplate.postForEntity(BANK_API_URL + "/accounts/new-buy",
                newBuyDTO,
                Boolean.class
        );

        return booleanResponseEntity.getBody();
    }
}
