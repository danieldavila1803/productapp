package com.example.productapp;

import com.example.productapp.entity.Product;
import com.example.productapp.repository.ProductRepository;
import com.example.productapp.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ProductServiceTest {
    @Test
    void testFindStock() {
        var repository = Mockito.mock(ProductRepository.class);

        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setNombre("Turron");
        mockProduct.setPrecio(12.0);
        mockProduct.setStock(3);

        Mockito.when(repository.findByStockLessThan(5))
                .thenReturn(Flux.just(mockProduct));

        var service = new ProductService(repository);

        StepVerifier.create(service.findStock())
                .expectNextMatches(p -> p.getStock() < 5 && p.getNombre().equals("Turron"))
                .verifyComplete();
    }
}