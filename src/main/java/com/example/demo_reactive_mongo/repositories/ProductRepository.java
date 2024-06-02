package com.example.demo_reactive_mongo.repositories;

import com.example.demo_reactive_mongo.entities.Product;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByCategory(String category);

    @Query("{'price': {$lte: ?0}}")
    Flux<Product> findByPriceLessThanEqual(Double maxPrice);
}
