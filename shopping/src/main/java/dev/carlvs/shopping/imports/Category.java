package dev.carlvs.shopping.imports;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Category {
    
    private Long id;
    private String name;
}
