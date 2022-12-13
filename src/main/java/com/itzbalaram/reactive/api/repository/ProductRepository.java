package com.itzbalaram.reactive.api.repository;

import com.itzbalaram.reactive.api.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
//    Flux<ProductDto> findByPriceRange(Range<Double> priceRange);
}
