package br.com.thiagomagdalena.encurtadorurl.usecase.impl;

import br.com.thiagomagdalena.encurtadorurl.model.ShortUrl;
import br.com.thiagomagdalena.encurtadorurl.repository.ShortUrlRepository;
import br.com.thiagomagdalena.encurtadorurl.usecase.ShortenUrlUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortenUrlUseCaseImpl implements ShortenUrlUseCase {

    private final ShortUrlRepository shortUrlRepository;
    @Value("${spring.application.url}")
    private String url;

    @Override
    public Mono<String> execute(String originaUrl) {
        final var shortCode = generateShortCode();
        final var shortUrl = buildShortUrl(originaUrl, shortCode);
        return shortUrlRepository.save(shortUrl)
                .thenReturn(String.format(url + shortCode));
    }

    private ShortUrl buildShortUrl(String originaUrl, String shortCode) {
        return ShortUrl.builder()
                .originalUrl(originaUrl)
                .shortCode(shortCode)
                .expirationDate(LocalDateTime.now().plusMonths(1))
                .isActive(Boolean.TRUE)
                .build();
    }

    private String generateShortCode() {
        final var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final var shortCode = new StringBuilder();
        final var random = new Random();
        for (int i = 0; i < 6; i++) {
            shortCode.append(chars.charAt(random.nextInt(chars.length())));
        }
        return shortCode.toString();
    }
}
