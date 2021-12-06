package hello.springdatareactiveredis;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CoffeeController {

    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.coffeeOps = coffeeOps;
    }

    @GetMapping("/coffees")
    public Flux<Coffee> all() {

        return coffeeOps.keys("*")
                .flatMap(coffeeOps.opsForValue()::get);
    }

    @GetMapping("/coffee")
    public Mono<Coffee> oneRandom() {

        return coffeeOps.randomKey()
                .flatMap(coffeeOps.opsForValue()::get);
    }

    @GetMapping("/hello")
    public Mono<String> hello() {

        return Mono.just("hello");
    }
}
