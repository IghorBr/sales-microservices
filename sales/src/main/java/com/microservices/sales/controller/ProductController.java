package com.microservices.sales.controller;

import com.microservices.sales.domain.BaseController;
import com.microservices.sales.dto.ProductDTO;
import com.microservices.sales.entity.Product;
import com.microservices.sales.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "Responsável por Pedidos")
public class ProductController extends BaseController<Product, ProductDTO> {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        super(Product.class, ProductDTO.class, productService);
        this.productService = productService;
    }
}
