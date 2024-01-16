package com.example.demo.controller;

import com.example.demo.repo.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MainController {
    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/hello")
    public Mono<String> hello(){
        System.out.println(orderRepository.findAll());
        System.out.println("inner /hello");
        Mono<String> mom = Flux.just("1","2","3","4")
                .flatMap(x -> {
                    System.out.println(x);
                    return Mono.just(x);
                }).then(Mono.just("1234"));
        mom.doOnSuccess(System.out::println)
                .doOnError(e -> {
                    System.out.println(123);
                }).subscribe();

        return Mono.just("12");

    }
    @GetMapping("/hello2")
    public ResponseEntity<String> hello2(){
        Mono<String> hello = hello();
        String result = hello.block();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/hello3")
    public String hello3(){
        System.out.println(123);
        return "Hello 3";
    }
}
