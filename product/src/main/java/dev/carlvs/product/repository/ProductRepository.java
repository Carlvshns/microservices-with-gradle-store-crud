package dev.carlvs.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.carlvs.product.domain.Category;
import dev.carlvs.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    public List<Product> findByCategory(Category category);
}
