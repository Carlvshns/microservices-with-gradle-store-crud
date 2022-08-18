package dev.carlvs.product.util;

import java.util.Date;

import dev.carlvs.product.domain.Category;
import dev.carlvs.product.domain.Product;

public class CreateProduct {
    
    public static Product createProduct(){
        Product product = Product.builder().name("Computer")
                                        .category(Category.builder().id(1L).build())
                                        .description("")
                                        .stock(10.0)
                                        .price(1240.99)
                                        .state("CREATED")
                                        .createAt(new Date())
                                        .build();
        return product;
    }
}
