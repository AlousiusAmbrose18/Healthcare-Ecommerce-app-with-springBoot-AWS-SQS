package com.tbd.health.controller;

import com.tbd.health.entity.Order;
import com.tbd.health.entity.Product;
import com.tbd.health.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product newProduct = productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

}
