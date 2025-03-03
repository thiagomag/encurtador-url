package br.com.thiagomagdalena.encurtadorurl.usecase.impl;

import br.com.thiagomagdalena.encurtadorurl.repository.ShortUrlRepository;
import br.com.thiagomagdalena.encurtadorurl.usecase.ExpireShortUrlsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpireShortUrlsUseCaseImpl implements ExpireShortUrlsUseCase {


    private final ShortUrlRepository shortUrlRepository;

    @Override
    public Mono<Void> execute(Void input) {
        return shortUrlRepository.findToExpire()
                .flatMap(shortUrl -> {
                    shortUrl.delete();
                    return shortUrlRepository.save(shortUrl);
                })
                .then();
    }
}
