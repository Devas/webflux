package com.devas.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class StudentApi {

    private Flux<Student> studentFlux;

    public StudentApi() {
        studentFlux = Flux.just(
                new Student("Jan", "Nowak"),
                new Student("Jan", "Nowak"),
                new Student("Jan", "Nowak")
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Student> getStudent() {
        return studentFlux.delayElements(Duration.ofSeconds(2));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Student> getStudent(@RequestBody Student student) {
        studentFlux = studentFlux.mergeWith(Mono.just(student));
        return studentFlux;
    }
}
