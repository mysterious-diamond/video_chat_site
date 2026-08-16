package com.aarons.videochat.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarons.videochat.entity.User;
import com.aarons.videochat.error.UnauthorizedException;
import com.aarons.videochat.repository.UserRepository;
import com.aarons.videochat.util.JwtUtils;

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
    public ResponseEntity<String> signin(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String password = (String) requestBody.get("password");

        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UnauthorizedException("User name or password is incorrect");
        }

        boolean isCorrectPassword = user.verifyPassword(password);
        if (!isCorrectPassword) {
            throw new UnauthorizedException("User name or password is incorrect");
        }

        return new ResponseEntity<String>(generateJwtToken(user), HttpStatus.OK);
    }

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String nickname = (String) requestBody.get("nickname");
        String password = (String) requestBody.get("password");

        User user = new User(name, nickname, password);
        userRepository.save(user);

        return new ResponseEntity<String>(generateJwtToken(user), HttpStatus.OK);
    }

    private String generateJwtToken(User user) {
        String nameToBeReturned = (user.getNickname() == null) ? user.getName() : user.getNickname();
        String jwtToken = jwtUtils.generateJwtToken(user.getId(), nameToBeReturned);
        return jwtToken;
    }
}
