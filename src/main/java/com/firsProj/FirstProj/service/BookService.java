package com.firsProj.FirstProj.service;

import com.firsProj.FirstProj.model.dto.BookRequestDto;
import com.firsProj.FirstProj.model.dto.BookResponseDto;
import com.firsProj.FirstProj.model.entity.BookEntity;
import com.firsProj.FirstProj.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<BookResponseDto> create(BookRequestDto book) {
        return bookRepository.save(new BookEntity(
                book.title(),
                book.author(),
                null))
                .map(b -> new BookResponseDto(
                        book.title(),
                        book.author(),
                        null
                ));
    }

    public Mono<Void> delete(Long bookId) {
        return bookRepository.findById(bookId)
                        .switchIfEmpty(
                                Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))
                        )
                .flatMap(bookRepository::delete);
    }

//    private BookResponseDto toDto(BookRequestDto book) {
//        return new BookResponseDto(
//                book.title(),
//                book.author(),
//
//        )
//    }

}
