package br.com.thiagomagdalena.encurtadorurl.usecase.impl;

import br.com.thiagomagdalena.encurtadorurl.repository.ShortUrlRepository;
import br.com.thiagomagdalena.encurtadorurl.usecase.GetOriginalUrlUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetOriginalUrlUseCaseImpl implements GetOriginalUrlUseCase {

    private final ShortUrlRepository shortUrlRepository;

    @Override
    public Mono<ResponseEntity<Object>> execute(String shortCode) {
        return shortUrlRepository.findByShortCodeAndDeletedTmspIsNull(shortCode)
                .map(shortUrl -> {
                    String originalUrl = shortUrl.getOriginalUrl();
                    // Adiciona o esquema "http://" se a URL não tiver um esquema
                    if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
                        originalUrl = "http://" + originalUrl;
                    }
                    return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
                })
                .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.notFound().build())));
    }
}
