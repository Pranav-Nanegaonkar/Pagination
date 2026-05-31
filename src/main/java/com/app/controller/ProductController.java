package com.app.controller;

import com.app.dto.PageRequestDTO;
import com.app.dto.PageResponseDTO;
import com.app.model.Product;
import com.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /*
     * Offset pagination
     * GET /api/v1/products?page=0&size=20&sortBy=createdAt&direction=desc
     */
    @GetMapping
    public ResponseEntity<PageResponseDTO<Product>> getProductsOffset(@Valid @RequestBody PageRequestDTO request) {
        return ResponseEntity.ok(productService.getProductsOffset(request));
    }
}
