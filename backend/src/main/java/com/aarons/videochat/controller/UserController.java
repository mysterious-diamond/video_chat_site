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
import com.aarons.videochat.utils.JwtUtils;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtUtils = new JwtUtils();
    }

    @PostMapping("/signin")
    public Optional<String> signin(@RequestBody Map<String, Object> requestBody) {
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

        String nameToBeReturned = (user.getNickname() == null) ? user.getName() : user.getNickname();
        return Optional.of(jwtUtils.generateJwtToken(user.getId(), nameToBeReturned));
    }

    @PostMapping("/api/signup")
    public String signup() {
        return "Running!";
    }
}
