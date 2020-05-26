package com.devas.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ApiClient {

    Logger logger = LoggerFactory.getLogger(ApiClient.class);

//    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        Flux<Student> studentFlux = WebClient.create()
                .get()
                .uri("http://localhost:8080/")
//                .exchange()
                .retrieve()
                .bodyToFlux(Student.class); // json to flux

        studentFlux.subscribe(student -> logger.info(student.toString()));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void create() {
        Flux<Student> studentFlux = WebClient.create()
                .post()
                .uri("http://localhost:8080/")
//                .exchange()
                .body(Mono.just(new Student("Przemek", "Bykowski")), Student.class)
                .retrieve()
                .bodyToFlux(Student.class); // json to flux

        studentFlux.subscribe(student -> logger.info(student.toString()));
    }
}
