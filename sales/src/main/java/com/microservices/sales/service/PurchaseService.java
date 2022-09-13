package com.microservices.sales.service;

import com.microservices.sales.domain.BaseService;
import com.microservices.sales.dto.NewPurchaseDTO;
import com.microservices.sales.entity.Purchase;

public interface PurchaseService extends BaseService<Purchase> {
    void createPurchase(NewPurchaseDTO dto);
}
