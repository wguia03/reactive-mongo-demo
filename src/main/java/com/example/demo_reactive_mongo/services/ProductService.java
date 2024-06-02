package com.example.demo_reactive_mongo.services;

import com.example.demo_reactive_mongo.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> getAllProducts();

    Mono<Product> getProductById(String id);

    Flux<Product> getProductsByCategory(String category);

    Flux<Product> getProductsByMaxPrice(Double maxPrice);

    Mono<Product> saveProduct(Product product);

    Mono<Product> updateProduct(String id, Product product);

    Mono<Void> deleteProduct(String id);
}
