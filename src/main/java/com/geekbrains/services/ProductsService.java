package com.geekbrains.services;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductsRepository;
import com.geekbrains.utils.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    public ProductsService(){
    }

    public List<Product> getAllProductsList(){
        return (List<Product>) productsRepository.findAll();
    }

//    public void addProduct(Product product){
//        productsRepository.save(product);
//    }
//
//    public void removeById(Long id){
//        productsRepository.deleteById(id);
//    }

    //get
    public Product getProductById(Long id){
        Optional<Product> product = productsRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id = " + id + " not found");
        }
        return product.get();
    }

    //post put
    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
    }

    //delete
    public void delete(Long id) {
        Optional<Product> product = productsRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id = " + id + " not found");
        }
        productsRepository.delete(product.get());
    }

}
