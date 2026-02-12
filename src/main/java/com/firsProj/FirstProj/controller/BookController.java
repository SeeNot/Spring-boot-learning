package com.firsProj.FirstProj.controller;

import com.firsProj.FirstProj.model.dto.BookRequestDto;
import com.firsProj.FirstProj.model.dto.BookResponseDto;
import com.firsProj.FirstProj.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
