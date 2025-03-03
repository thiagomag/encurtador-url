package br.com.thiagomagdalena.encurtadorurl.usecase;

import reactor.core.publisher.Mono;

public interface ShortenUrlUseCase extends UseCase<String, Mono<String>> {
}
