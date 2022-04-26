package com.example.userservicedemo.controller;

import com.example.userservicedemo.dto.Product;
import com.example.userservicedemo.model.User;
import com.example.userservicedemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

    private String baseUrl="http://localhost:8000/product-service/api";


    @Autowired
    WebClient webClient;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/save")
    public User createUser(@RequestBody User user){

        Product pro = new Product();

        User user1 = userRepo.save(user);
        pro.setId(2l);
        pro.setUserid(user1.getId());
        pro.setName("kartik");
        Product products= createProduct(pro);
         return user1;
    }

    @GetMapping("/user")
    public String getUser(){
        return "name";
    }


    public Product createProduct(Product product){
        Mono<Product>  productMono =  webClient
                .post()
                .uri(baseUrl+"/save")
                .body(Mono.just(product),Product.class)
                .retrieve()
                .bodyToMono(Product.class);
       Product product1 = productMono.block();
        return product1;
    }

}
