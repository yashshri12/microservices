package com.example.userservicedemo.controller;

import com.example.userservicedemo.dto.Product;
import com.example.userservicedemo.model.User;
import com.example.userservicedemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

        //Product pro = new Product();

        User user1 = userRepo.save(user);
//        pro.setId(5l);
//        pro.setUserid(user1.getId());
//        pro.setName("kartik");
//        Product products= createProduct(pro);
        return user1;
    }

    @GetMapping("/user")
    public String getUser(){
        return "name";
    }


    @PostMapping("/{id}/product")
    public Product createProduct(@PathVariable("id") Long id,@RequestBody Product product){
        User user=userRepo.findById(id).get();
         product.setUserid(user.getId());
        Mono<Product>  productMono =  webClient
                .post()
                .uri(baseUrl+"/save")
                .body(Mono.just(product),Product.class)
                .retrieve()
                .bodyToMono(Product.class);
       Product product1 = productMono.block();
        return product1;
    }

    @GetMapping("/allproduct")
   public Flux<Product> getAllProduct(){
      return webClient.get().uri(baseUrl+"/all").retrieve().bodyToFlux(Product.class);

   }

   @GetMapping("/{id}/product/{proId}")
   public Product getOneProduct(@PathVariable("id") Long id,@PathVariable("proId") Long productId){
       User user=userRepo.findById(id).get();


       Mono<Product>  productMono =  webClient
               .get()
               .uri(baseUrl+"/get/"+productId)
               .retrieve()
               .bodyToMono(Product.class);
       Product product1 = productMono.block();
       return product1;
   }

   @PutMapping("/updateproduct/{id}/pro/{proId}")
   public Product update(@PathVariable("id") Long id,@PathVariable("proId") Long productId,@RequestBody Product product){

       User user=userRepo.findById(id).get();
      // product.setUserid(user.getId());

       Mono<Product>  productMono =  webClient
               .put()
               .uri(baseUrl+"/update/"+productId)
               .body(Mono.just(product),Product.class)
               .retrieve()
               .bodyToMono(Product.class);
       Product product1 = productMono.block();

       return product1;
   }

   @DeleteMapping("/{id}/product/{productId}")
   public Mono<Void> deleteProduct(@PathVariable("id") Long id, @PathVariable("productId") Long productId){
       User user=userRepo.findById(id).get();

       return webClient.delete().uri(baseUrl+"/delete/"+productId).retrieve().bodyToMono(Void.class);

   }
}
