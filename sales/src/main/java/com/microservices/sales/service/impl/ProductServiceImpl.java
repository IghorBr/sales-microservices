package com.microservices.sales.service.impl;

import com.microservices.sales.domain.BaseServiceImpl;
import com.microservices.sales.entity.Product;
import com.microservices.sales.repository.ProductRepository;
import com.microservices.sales.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }
}
