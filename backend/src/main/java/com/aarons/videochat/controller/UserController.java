package com.aarons.videochat.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarons.videochat.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String password = (String) requestBody.get("password");

        String jwtToken = userService.validateUserAndGetJwtToken(name, password);
        return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
    }

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String nickname = (String) requestBody.get("nickname");
        String password = (String) requestBody.get("password");

        String jwtToken = userService.registerNewUserAndGetJwtToken(name, nickname, password);
        return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
    }
}
