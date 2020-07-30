package com.mx.daac.repository;

import com.mx.daac.model.Link;
import reactor.core.publisher.Mono;

public interface LinkRepository {

    Mono<Link> save(Link link);

    Mono<Link> findByKey(String key);
}
