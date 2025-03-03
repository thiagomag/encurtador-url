package br.com.thiagomagdalena.encurtadorurl.repository;

import br.com.thiagomagdalena.encurtadorurl.model.ShortUrl;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShortUrlRepository extends ReactiveCrudRepository<ShortUrl, Long> {

    Mono<ShortUrl> findByShortCodeAndDeletedTmspIsNull(String shortCode);

    @Query("select s.* from short_url s " +
            "where s.expiration_date <= now() " +
            "and s.is_active = true;")
    Flux<ShortUrl> findToExpire();
}
