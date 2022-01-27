package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductSpecs;
import com.geekbrains.services.CategoryService;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 5;

    private ProductService productService;
    private CategoryService categoryService;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String shopPage(Model model,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "word", required = false) String word,
                           @RequestParam(value = "min", required = false) Double min,
                           @RequestParam(value = "max", required = false) Double max
    ) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Product> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();
        if (word != null) {
            spec = spec.and(ProductSpecs.titleContains(word));
            filters.append("&word=" + word);
        }
        if (min != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
            filters.append("&min=" + min);
        }
        if (max != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
            filters.append("&max=" + max);
        }

        Page<Product> products = productService.getProductsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);

        model.addAttribute("products", products.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", products.getTotalPages());

        model.addAttribute("filters", filters.toString());

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);

        return "shop-page";
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") int id){
        productService.delete((long) id);
        return "redirect:/products";
    }

        @GetMapping(value = "/products/add")
        public String showProductForm(Model model) {
            Product product = new Product();
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "product-form";
        }

        @PostMapping("/products/add")
        public String addProduct(@ModelAttribute(value="product") Product product) {
            product.setId(0L);
            productService.add(product);
            return "redirect:/products";
        }




}