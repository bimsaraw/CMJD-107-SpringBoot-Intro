package com.ijse.springintro.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String description;
    
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderedProducts")
    private List<Order> orders;
}
