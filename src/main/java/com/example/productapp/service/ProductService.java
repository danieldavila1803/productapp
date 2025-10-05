package com.example.productapp.service;

import com.example.productapp.entity.Product;
import com.example.productapp.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<Product> findAll() {
        return repository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<Product> create(Product product) {
        return repository.save(product);
    }

    public Mono<Product> update(Long id, Product product) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setNombre(product.getNombre());
                    existing.setPrecio(product.getPrecio());
                    existing.setStock(product.getStock());
                    return repository.save(existing);
                });
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }

    public Flux<Product> findStock() {
        return repository.findByStockLessThan(5);
    }
}