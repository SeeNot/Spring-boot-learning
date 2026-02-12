package com.firsProj.FirstProj.controller;

import com.firsProj.FirstProj.model.dto.UserResponseDto;
import com.firsProj.FirstProj.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<UserResponseDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/email/{userEmail}")
    public Mono<UserResponseDto> getUserByEmail(@PathVariable String userEmail) {
        return userService.findByEmail(userEmail);
    }
}
