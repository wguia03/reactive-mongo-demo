package com.example.demo_reactive_mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "products")
public class Product {
    @Id
    private String productId;
    private String name;
    private double price;
    private int stock;
    private String category;
}
