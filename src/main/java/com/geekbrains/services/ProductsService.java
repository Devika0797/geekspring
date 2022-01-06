package com.geekbrains.services;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addProduct(Product product){
        productsRepository.save(product);
    }

    public void removeById(Long id){
        productsRepository.deleteById(id);
    }

}
