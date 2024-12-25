package com.rafaelswr.springsecurityindeep.FilteringMethodAuth;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> toSellProducts(List<Product> productToSell) {
        return productToSell;
    }
}
