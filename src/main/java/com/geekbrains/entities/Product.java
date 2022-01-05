package com.geekbrains.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")//, insertable = false, updatable = false)
    private Long id;

    @Column(name = "item")
    private String item;


    @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id")
    private Category category;

    @Cascade({org.hibernate.annotations.CascadeType.DELETE, org.hibernate.annotations.CascadeType.DETACH})
    private Collection<Category> categories;

    public Collection<Category> getCategories(){
        return categories;
    }
    public void setCategories(List<Category> categories){
        this.categories = categories;
    }



    // get set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Product(){
    }


    @Override
    public String toString() {
        return "product_id=" + id + ", categories" + categories.size() + "}";
    }
}
