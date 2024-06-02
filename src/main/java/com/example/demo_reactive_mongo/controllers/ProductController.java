package com.example.demo_reactive_mongo.controllers;

import com.example.demo_reactive_mongo.entities.Product;
import com.example.demo_reactive_mongo.services.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Flux<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity<Mono<Product>> getProductById(@RequestParam String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/byCat")
    public ResponseEntity<Flux<Product>> getProductsByCategory(@RequestParam String category) {
        return new ResponseEntity<>(productService.getProductsByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/byMaxPrice")
    public ResponseEntity<Flux<Product>> getProductsByMaxPrice(@RequestParam Double maxPrice) {
        return new ResponseEntity<>(productService.getProductsByMaxPrice(maxPrice), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mono<Product>> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
    }

    @PutMapping
    public Mono<ResponseEntity<Product>> updateProduct(@RequestParam String id, @RequestBody Product product) {
        return productService.updateProduct(id, product)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)));
    }

    @DeleteMapping
    public ResponseEntity<Mono<Void>> deleteProduct(@RequestParam String id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
