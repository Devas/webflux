package com.devas.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class WebfluxApplicationTests {

    @Test
    void contextLoads() {
        Flux.just("Jan", "Karol", "Basia")
                .filter(s -> s.contains("B"))
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        Mono.just("Jan");
    }

}
