package dev.carlvs.product.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.carlvs.product.domain.Product;
import dev.carlvs.product.util.CreateProduct;

@DataJpaTest
public class ProductRepositoryMockTest {
    
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProducts(){
        
        Product product = CreateProduct.createProduct();

        productRepository.save(product);

        List<Product> founds = productRepository.findByCategory(product.getCategory());

        Assertions.assertEquals(founds.size(), 3);
    }
}
