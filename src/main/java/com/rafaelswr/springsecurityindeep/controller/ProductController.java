package com.rafaelswr.springsecurityindeep.controller;

import com.rafaelswr.springsecurityindeep.FilteringMethodAuth.Product;
import com.rafaelswr.springsecurityindeep.FilteringMethodAuth.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/sell")
    public List<Product> sellProducts(){
        List<Product> productToSell = new ArrayList<>();

        productToSell.add(new Product("emma", "beer"));
        productToSell.add(new Product("natalie", "pizza"));
        productToSell.add(new Product("emma", "bread"));

        return productService.toSellProducts(productToSell);
    }

}
