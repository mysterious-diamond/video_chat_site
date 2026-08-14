package com.aarons.videochat.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarons.videochat.entity.User;
import com.aarons.videochat.repository.UserRepository;
import com.nimbusds.jwt.JWT;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signin")
    public Optional<JWT> signin(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String password = (String) requestBody.get("password");

        User user = userRepository.findByName(name);
        if (user == null) {
            return Optional.empty();
        }

        boolean isCorrectPassword = user.verifyPassword(password);
        if (!isCorrectPassword) {
            return Optional.empty();
        }

        return Optional.of();

    }

    @PostMapping("/api/signup")
    public String signup() {
        return "Running!";
    }
}
