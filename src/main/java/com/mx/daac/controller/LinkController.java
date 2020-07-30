package com.mx.daac.controller;

import com.mx.daac.model.CreateLinkRequest;
import com.mx.daac.model.CreateLinkResponse;
import com.mx.daac.service.LinkService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request){
       return linkService.shortenLink(request.getLink())
        .map(CreateLinkResponse::new);

    }

    @GetMapping("/{key}")
    Mono<ResponseEntity<Object>> getLink(@PathVariable String key) {
        return linkService.getOriginalLink(key)
                .map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                        .header("Location", link.getOriginalLink())
                        .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
