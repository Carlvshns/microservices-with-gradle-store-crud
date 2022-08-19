package dev.carlvs.shopping.client;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import dev.carlvs.shopping.imports.Category;
import dev.carlvs.shopping.imports.Product;

@Component
public class ProductHystrixFallbackFactory implements ProductClient{

    @Override
    public ResponseEntity<Product> getProduct(Long id) {
        Product product = Product.builder().name("none")
                                .description("none")
                                .state("none")
                                .createAt(new Date())
                                .category(Category.builder().name("none").build())
                                .build();

        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> addStockInProduct(Long id, Double quantity) {
        Product product = Product.builder().name("none")
                                        .description("none")
                                        .state("none")
                                        .createAt(new Date())
                                        .category(Category.builder().name("none").build())
                                        .build();

        return ResponseEntity.ok(product);
    }
    
}
