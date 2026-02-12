package com.firsProj.FirstProj.controller;

import com.firsProj.FirstProj.model.dto.UserRegistrationRequestDto;
import com.firsProj.FirstProj.model.entity.UserEntity;
import com.firsProj.FirstProj.model.dto.UserDto;
import com.firsProj.FirstProj.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public Flux<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/email/{userEmail}")
    public Mono<UserDto> getUserByEmail(@PathVariable String userEmail) {
        return userService.findByEmail(userEmail);
    }
}
