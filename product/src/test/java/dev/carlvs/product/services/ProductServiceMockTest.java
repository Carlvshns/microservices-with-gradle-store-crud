package dev.carlvs.product.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import dev.carlvs.product.domain.Product;
import dev.carlvs.product.repository.ProductRepository;
import dev.carlvs.product.util.CreateProduct;

@SpringBootTest
public class ProductServiceMockTest {
    
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(productRepository);

        Product computer = CreateProduct.createProduct();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(computer));

        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    public void getProductReturnObjProductIfProductsExitsWhenSuccessful(){
        Product productFound = productService.getProduct(1L);

        Assertions.assertEquals("Computer", productFound.getName());
    }

    @Test
    public void addStockPersistAndUpdateQuantityStockIfProductsExitsAndReturnObjProductWhenSuccesful(){
        Product productWithNewStock = productService.addStock(1L, 8.0);

        Assertions.assertEquals(productWithNewStock.getStock(), 18.0);
    }
}
