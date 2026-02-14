package com.firsProj.FirstProj.service;

import com.firsProj.FirstProj.model.dto.BookRequestDto;
import com.firsProj.FirstProj.model.dto.BookResponseDto;
import com.firsProj.FirstProj.model.entity.BookEntity;
import com.firsProj.FirstProj.model.entity.UserEntity;
import com.firsProj.FirstProj.repository.BookRepository;
import com.firsProj.FirstProj.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(
            BookRepository bookRepository,
            UserRepository userRepository
    ) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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
        return findBookOrThrow(bookId).flatMap(bookRepository::delete);
    }

    public Mono<BookResponseDto> assignBookToCurrent(Long bookId, String email) {
        return Mono.zip(
                findBookOrThrow(bookId),
                userRepository.findByEmail(email)
        ).flatMap( tuple -> {
            BookEntity book = tuple.getT1();
            UserEntity user = tuple.getT2();

            book.setUserId(user.getId());
            return bookRepository.save(book)
                    .map(bookEntity -> new BookResponseDto(
                            bookEntity.getTitle(),
                            bookEntity.getAuthor(),
                            user.getUsername()
                    ));
            }
        );
    }

    private Mono<BookEntity> findBookOrThrow(Long bookId) {
        return bookRepository.findById(bookId)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Book with id %s not found".formatted(bookId)
                                )
                        )
                );
    }

//    private BookResponseDto toDto(BookRequestDto book) {
//        return new BookResponseDto(
//                book.title(),
//                book.author(),
//
//        )
//    }

}
