package dev.carlvs.product.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.carlvs.product.domain.Category;
import dev.carlvs.product.domain.Product;
import dev.carlvs.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {

        product.setState("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        Product productDB = getProduct(product.getId());

        if(productDB == null){
            return null;
        }

        return productRepository.save(product);
    }

    @Override
    public Product setDeletedStateProduct(Long id) {
        Product productDB = getProduct(id);

        if(productDB == null){
            return null;
        }

        productDB.setState("DELETED");
        return productDB;
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product addStock(Long id, Double quantity) {
        Product productDB = getProduct(id);

        if(productDB == null){
            return null;
        }

        productDB.setStock(productDB.getStock() + quantity);
        return productRepository.save(productDB);
    }
    
}
