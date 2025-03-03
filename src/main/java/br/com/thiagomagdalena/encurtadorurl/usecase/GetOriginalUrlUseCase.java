package br.com.thiagomagdalena.encurtadorurl.usecase;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface GetOriginalUrlUseCase extends UseCase<String, Mono<ResponseEntity<Object>>> {
}
