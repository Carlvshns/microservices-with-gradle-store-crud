package dev.carlvs.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.carlvs.shopping.imports.Product;

@FeignClient(name = "product")
@RequestMapping(value = "/products")
public interface ProductClient {
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id")Long id);

    @PutMapping(path = "/{id}/stock")
    public ResponseEntity<Product> addStockInProduct(@PathVariable(name = "id") Long id, 
                                                    @RequestParam(name = "quantity") Double quantity);
}
