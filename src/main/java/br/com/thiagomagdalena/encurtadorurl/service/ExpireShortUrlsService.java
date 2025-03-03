package br.com.thiagomagdalena.encurtadorurl.service;

import br.com.thiagomagdalena.encurtadorurl.usecase.ExpireShortUrlsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ExpireShortUrlsService {

    private final ExpireShortUrlsUseCase expireShortUrlsUseCase;

    @Scheduled(cron = "0 0 0 * * ?")
    public Mono<Void> expireShortUrls() {
        return expireShortUrlsUseCase.execute(null);
    }
}