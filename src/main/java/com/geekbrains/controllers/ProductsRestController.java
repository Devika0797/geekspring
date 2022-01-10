package com.geekbrains.controllers;


import com.geekbrains.entities.Product;
import com.geekbrains.services.ProductsService;
import com.geekbrains.utils.ProductNotFoundException;
import com.geekbrains.utils.ProductsErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api**")
@RestController
public class ProductsRestController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productsService.getProductById(productId);
    }


    @GetMapping("/products")
    public Product addProduct(@RequestBody Product theProduct){
        theProduct.setId(0L);
        theProduct = productsService.saveOrUpdate(theProduct);
        return theProduct;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product theProduct) {
        theProduct.setId(0L);
        theProduct = productsService.saveOrUpdate(theProduct);
        return theProduct;
    }

    @PutMapping(path = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product theProduct) {
        theProduct = productsService.saveOrUpdate(theProduct);
        return theProduct;
    }

    @DeleteMapping("/products/{productId}")
    public int deleteProduct(@PathVariable Long productId) {
        productsService.delete(productId);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(ProductNotFoundException e) {
        ProductsErrorResponse productsErrorResponse = new ProductsErrorResponse();
        productsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        productsErrorResponse.setMessage(e.getMessage());
        productsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.NOT_FOUND);
    }


}
