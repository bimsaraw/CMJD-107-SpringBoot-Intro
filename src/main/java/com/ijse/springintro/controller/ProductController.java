package com.ijse.springintro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.dto.ProductReqDTO;
import com.ijse.springintro.entity.Category;
import com.ijse.springintro.entity.Product;
import com.ijse.springintro.service.CategoryService;
import com.ijse.springintro.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductReqDTO productReqDTO) {
        try {
            Product newProduct = new Product();
            newProduct.setName(productReqDTO.getName());
            newProduct.setPrice(productReqDTO.getPrice());
            newProduct.setDescription(productReqDTO.getDescription());
            
            Category category = categoryService.getCategoryById(productReqDTO.getCategoryId());

            newProduct.setCategory(category);
            
            Product createdProduct = productService.createProduct(newProduct);
            return ResponseEntity.status(201).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductReqDTO productReqDTO, @PathVariable Long productId) {

        try {
            Product existingProduct = productService.getProductById(productId);
            if (existingProduct != null) {
                existingProduct.setName(productReqDTO.getName());
                existingProduct.setPrice(productReqDTO.getPrice());
                existingProduct.setDescription(productReqDTO.getDescription());
                
                Category category = categoryService.getCategoryById(productReqDTO.getCategoryId());
                existingProduct.setCategory(category);
                
                Product updatedProduct = productService.updateProduct(productId, existingProduct);
                return ResponseEntity.status(200).body(updatedProduct);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
