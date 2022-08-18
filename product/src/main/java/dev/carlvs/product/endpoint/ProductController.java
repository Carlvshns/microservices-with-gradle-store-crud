package dev.carlvs.product.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.carlvs.product.domain.Category;
import dev.carlvs.product.domain.Product;
import dev.carlvs.product.error.ErrorMessage;
import dev.carlvs.product.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts(@RequestParam(name = "categoryId", required = false)  Long categoryId){
        
        List<Product> products = new ArrayList<>();
        
        if(categoryId == null){

            products = productService.listAllProduct();

            if(products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{

            products = productService.findByCategory(Category.builder()
                                                            .id(categoryId)
                                                            .build());

            if(products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id")Long id){

        Product product = productService.getProduct(id);

        if(product == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){

        if(result.hasErrors()){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.formatMessage(result));
        }

        Product productCreated = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody Product product){

        product.setId(id);

        Product updatedProduct = productService.updateProduct(product);

        if(updatedProduct == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        
        Product deletedProduct = productService.setDeletedStateProduct(id);

        if(deletedProduct == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deletedProduct);
    }

    @PutMapping(path = "/{id}/stock")
    public ResponseEntity<Product> addStockInProduct(@PathVariable(name = "id") Long id, @RequestParam(name = "quantity") Double quantity){

        Product productWithNewStock = productService.addStock(id, quantity);

        if(productWithNewStock == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productWithNewStock);
    }
}
