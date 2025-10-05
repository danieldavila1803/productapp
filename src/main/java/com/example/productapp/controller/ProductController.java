package com.example.productapp.controller;

import com.example.productapp.entity.Product;
import com.example.productapp.service.ExternalExchangeService;
import com.example.productapp.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService ps;
    private final ExternalExchangeService es;

    public ProductController(ProductService ps, ExternalExchangeService es) {
        this.ps = ps;
        this.es = es;
    }

    @GetMapping public Flux<Product> all() { return ps.findAll(); }
    @GetMapping("/{id}") public Mono<Product> byId(@PathVariable Long id) { return ps.findById(id); }
    @PostMapping public Mono<Product> create(@RequestBody Product p) { return ps.create(p); }
    @PutMapping("/{id}") public Mono<Product> update(@PathVariable Long id, @RequestBody Product p) { return ps.update(id, p); }
    @DeleteMapping("/{id}") public Mono<Void> delete(@PathVariable Long id) { return ps.delete(id); }
    @GetMapping("/stock") public Flux<Product> stock() { return ps.findStock(); }

    @GetMapping("/convert")
    public Flux<Map<String, Object>> converted() {
        return es.getUsdToPen()
                .flatMapMany(rates ->
                        ps.findAll()
                                .map(product -> Map.of(
                                        "id", product.getId(),
                                        "nombre", product.getNombre(),
                                        "precioPEN", product.getPrecio(),
                                        "precioUSD", product.getPrecio() / rates,
                                        "stock", product.getStock()
                                ))
                );
    }
}