package com.ijse.springintro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.dto.OrderDTO;
import com.ijse.springintro.entity.Order;
import com.ijse.springintro.entity.Product;
import com.ijse.springintro.service.OrderService;
import com.ijse.springintro.service.ProductService;

@RestController
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        List<Long> productIds = orderDTO.getProductIds();

        Order order = new Order(); //initialize new order
        order.setTotalPrice(0.0);
        List<Product> orderedProducts = new ArrayList<>(); //initialize orderedProducts array

        productIds.forEach(productId -> {
            //get product by Id
            Product product = productService.getProductById(productId);
            
            if(product != null) {
                //add product to the order
                orderedProducts.add(product);
                order.setTotalPrice(order.getTotalPrice() + product.getPrice());
                //calculate total price
            }
        });
        
        order.setOrderedProducts(orderedProducts); //add orderedProducts list to orders object
        orderService.createOrder(order); //save new order       
        return ResponseEntity.status(201).body(order);
    }

}
