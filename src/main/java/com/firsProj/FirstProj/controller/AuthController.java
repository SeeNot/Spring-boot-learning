package com.firsProj.FirstProj.controller;

import com.firsProj.FirstProj.model.dto.UserDto;
import com.firsProj.FirstProj.model.dto.UserLoginDto;
import com.firsProj.FirstProj.model.dto.UserRegistrationRequestDto;
import com.firsProj.FirstProj.repository.UserRepository;
import com.firsProj.FirstProj.service.JwtService;
import com.firsProj.FirstProj.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        UserService userService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@Valid @RequestBody UserLoginDto request) {
        return userRepository.findByEmail(request.email())
                .filter(user -> passwordEncoder.matches(request.password(), user.getPassword()))
                .map(user -> Map.of("token", jwtService.generateToken(user.getEmail())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials")));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserDto> createUser(@Valid @RequestBody UserRegistrationRequestDto user) {
        return userService.saveUser(user);
    }
}
