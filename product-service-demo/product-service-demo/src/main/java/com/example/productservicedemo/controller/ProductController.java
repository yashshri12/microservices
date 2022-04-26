package com.example.productservicedemo.controller;

import com.example.productservicedemo.model.Product;
import com.example.productservicedemo.repo.Productrepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<Product> getAll(){
        return productrepo.findAll();
    }

    @GetMapping("/get/{id}")
    public Product getOne(@PathVariable("id") Long id){
        return  productrepo.findById(id).get();
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product){
        Product product1=productrepo.findById(id).get();

        product1.setName(product.getName());


        return productrepo.save(product1);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteproduct(@PathVariable("id") Long id){
        productrepo.deleteById(id);
    }
}
