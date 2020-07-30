package com.mx.daac.repository;

import com.mx.daac.model.Link;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class RedisLinkRepository  implements  LinkRepository{

    private final ReactiveRedisOperations<String, String> operations;

    @Override
    public Mono<Link> save(Link link) {
        return operations.opsForValue()
                .set(link.getKey(), link.getOriginalLink())
                .map(__ -> link);
    }

    @Override
    public Mono<Link> findByKey(String key) {
        return operations.opsForValue()
                .get(key)
                .map(result -> new Link(result, key));
    }
}
