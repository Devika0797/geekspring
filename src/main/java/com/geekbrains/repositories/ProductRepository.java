package com.geekbrains.repositories;
import com.geekbrains.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAll();
    Product findAlById(long id);
    void deleteById(long id);

    boolean findOneByTitle(String title);
}
