package br.com.thiagomagdalena.encurtadorurl.controller;

import br.com.thiagomagdalena.encurtadorurl.usecase.GetOriginalUrlUseCase;
import br.com.thiagomagdalena.encurtadorurl.usecase.ShortenUrlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UrlShortenerController {

    private final ShortenUrlUseCase shortenUrlUseCase;
    private final GetOriginalUrlUseCase getOriginalUrlUseCase;

    @PostMapping("/shorten")
    public Mono<String> shorten(@RequestBody String orignalUrl) {
        return shortenUrlUseCase.execute(orignalUrl);
    }

    @GetMapping("/{shortCode}")
    public Mono<ResponseEntity<Object>> getOriginalUrl(@PathVariable String shortCode) {
        return getOriginalUrlUseCase.execute(shortCode);
    }
}
