package dev.carlvs.product.services;

import java.util.List;

import dev.carlvs.product.domain.Product;
import dev.carlvs.product.domain.Category;


public interface ProductService {
    
    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product setDeletedStateProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product addStock(Long id, Double quantity);
}
