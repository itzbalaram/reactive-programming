package com.itzbalaram.reactive.api;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("itzbalaram")
                //.then(Mono.error(new RuntimeException("Exception occurred")))
                .log();

        monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> monoString = Flux.just("Spring", "Microservice", "Gradle", "Docker")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .concatWithValues("Devops")
                .log();

        monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }
}
