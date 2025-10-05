package com.example.productapp.repository;

import com.example.productapp.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long>{
    Flux<Product> findByStockLessThan(Integer stock);
}