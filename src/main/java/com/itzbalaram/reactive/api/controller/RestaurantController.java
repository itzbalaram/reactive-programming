package com.itzbalaram.reactive.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @GetMapping("/test")
    public Mono<String> testRestaurantResponse() {
        return Mono.just("Executed Successfully......");
    }

}
