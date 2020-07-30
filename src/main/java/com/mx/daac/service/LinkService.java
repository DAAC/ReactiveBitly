package com.mx.daac.service;

import com.mx.daac.model.Link;
import com.mx.daac.repository.LinkRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LinkService {

    @Value("${app.baseUrl}")
    private final String baseUrl;
    private final LinkRepository linkRepository;

    public Mono<String> shortenLink(String link){
        String ramdomKey = RandomStringUtils.randomAlphabetic(6);
        return linkRepository.save(new Link(link , ramdomKey))
                .map(result -> baseUrl + result.getKey());
    }

    public Mono<Link> getOriginalLink(String key) {
        return linkRepository.findByKey(key);
    }

}
