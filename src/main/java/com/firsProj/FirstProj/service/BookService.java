package com.firsProj.FirstProj.service;

import com.firsProj.FirstProj.model.dto.BookRequestDto;
import com.firsProj.FirstProj.model.dto.BookResponseDto;
import com.firsProj.FirstProj.model.entity.BookEntity;
import com.firsProj.FirstProj.repository.BookRepository;
import org.springframework.stereotype.Service;
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

//    private BookResponseDto toDto(BookRequestDto book) {
//        return new BookResponseDto(
//                book.title(),
//                book.author(),
//
//        )
//    }

}
