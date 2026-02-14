package com.firsProj.FirstProj.controller;

import com.firsProj.FirstProj.model.dto.BookRequestDto;
import com.firsProj.FirstProj.model.dto.BookResponseDto;
import com.firsProj.FirstProj.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Mono<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto request) {
        return bookService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable Long id) {
        return bookService.delete(id);
    }

    @PatchMapping("/assign/{id}")
    public Mono<BookResponseDto> assignBookToCurrent(@PathVariable Long id, @AuthenticationPrincipal String email) {
        if (email == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        }
        return bookService.assignBookToCurrent(id, email);
    }
}
