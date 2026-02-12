package com.firsProj.FirstProj.model.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class UserEntity {
    @Id
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(max = 50)
    @Column("username")
    private String username;

    @NotBlank
    @Email(message = "Invalid email format")
    @Column("email")
    private String email;

    @NotBlank
    @Column("password")
    private String password;

    public UserEntity() {
    }

    public UserEntity(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return  email;
    }
}
