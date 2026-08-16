package com.aarons.videochat.entity;

import com.aarons.videochat.error.BadRequestException;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String name, String nickname, String password) {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("User name field is empty");
        } else if (password == null || name.isEmpty()) {
            throw new BadRequestException("User password field is empty");
        } else if (name.length() <= 4) {
            throw new BadRequestException("User name have more than 4 characters");
        }

        this.name = name;
        this.nickname = nickname;
        this.password = password;

    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

}
