package com.firsProj.FirstProj.repository;

import com.firsProj.FirstProj.model.entity.BookEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends R2dbcRepository<BookEntity, Long> {
    Flux<BookEntity> findByUserId(Long userId);
}
