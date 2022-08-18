package dev.carlvs.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlvs.product.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
