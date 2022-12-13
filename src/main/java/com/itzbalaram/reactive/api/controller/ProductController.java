package com.itzbalaram.reactive.api.controller;

import com.itzbalaram.reactive.api.dto.ProductDto;
import com.itzbalaram.reactive.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Flux<ProductDto> fetchProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> fetchProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

//    @GetMapping("/product-range")
//    public Flux<ProductDto> fetchProductInRange(@RequestParam double min, @RequestParam double max) {
//        return productService.getProductBetweenRange(min, max);
//    }

    @PostMapping("/add")
    public Mono<ProductDto> addProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/modify/{id}")
    public Mono<ProductDto> modifyProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id) {
        return productService.updateProduct(productDtoMono, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<String> removeProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return Mono.just("Product with id: "+id+" is deleted successfully !");
    }

}
