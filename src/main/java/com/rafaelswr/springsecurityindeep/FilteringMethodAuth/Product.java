package com.rafaelswr.springsecurityindeep.FilteringMethodAuth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String owner;
    private String product;

}
