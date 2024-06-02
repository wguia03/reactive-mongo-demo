package com.example.demo_reactive_mongo.services;

import com.example.demo_reactive_mongo.entities.Product;
import com.example.demo_reactive_mongo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll()
                .delayElements(java.time.Duration.ofMillis(500));
    }

    @Override
    public Mono<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Flux<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Flux<Product> getProductsByMaxPrice(Double maxPrice) {
        return productRepository.findByPriceLessThanEqual(maxPrice);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        if (product.getProductId() != null) {
            return getProductById(product.getProductId())
                    .switchIfEmpty(productRepository.save(product));
        } else {
            return productRepository.save(product);
        }
    }

    @Override
    public Mono<Product> updateProduct(String id, Product product) {
        return getProductById(id)
                .flatMap(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setStock(product.getStock());
                    p.setCategory(product.getCategory());
                    return productRepository.save(p);
                });
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return getProductById(id).flatMap(productRepository::delete);
    }
}
