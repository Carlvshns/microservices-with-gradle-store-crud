package dev.carlvs.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.carlvs.shopping.imports.Customer;

@FeignClient(name = "customer", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
}
