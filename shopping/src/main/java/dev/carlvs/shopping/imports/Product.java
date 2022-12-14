package dev.carlvs.shopping.imports;

import java.util.Date;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Product {
    
    public Long id; 
    public String name; 
    public String description; 
    public Double stock; 
    public Double price; 
    public String state; 
    public Date createAt;
    public Category category;
}
