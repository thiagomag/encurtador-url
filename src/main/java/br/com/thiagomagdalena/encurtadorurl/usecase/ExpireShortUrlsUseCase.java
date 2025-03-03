package br.com.thiagomagdalena.encurtadorurl.usecase;

import reactor.core.publisher.Mono;

public interface ExpireShortUrlsUseCase extends UseCase<Void, Mono<Void>> {
}
