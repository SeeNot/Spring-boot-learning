package com.firsProj.FirstProj.service;

import com.firsProj.FirstProj.model.dto.UserRegistrationRequestDto;
import com.firsProj.FirstProj.model.entity.UserEntity;
import com.firsProj.FirstProj.model.dto.UserResponseDto;
import com.firsProj.FirstProj.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Flux<UserResponseDto> findAllUsers() {
        return userRepository.findAll().map(this::toDto);
    }

    public Mono<UserResponseDto> saveUser(UserRegistrationRequestDto user) {

        UserEntity savableUser = new UserEntity();
        String hashedPassword = encoder.encode(user.password());

        savableUser.setUsername(user.username());
        savableUser.setEmail(user.email());
        savableUser.setPassword(hashedPassword);
        savableUser.setId(null);

        return userRepository.save(savableUser).map(this::toDto);
    }

    public Mono<UserResponseDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::toDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
    }

    private UserResponseDto toDto(UserEntity entity) {
        return new UserResponseDto(
                entity.getUsername(),
                entity.getEmail()
        );
    }
}
