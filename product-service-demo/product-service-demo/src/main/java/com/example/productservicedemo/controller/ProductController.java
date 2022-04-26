package com.example.productservicedemo.controller;

import com.example.productservicedemo.model.Product;
import com.example.productservicedemo.repo.Productrepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private Productrepo productrepo;

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product){
        return productrepo.save(product);
    }

    @GetMapping("/name")
    public String getproduct(){
        return "product";
    }
}
