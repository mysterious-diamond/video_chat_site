package com.aarons.videochat.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarons.videochat.service.UserService;

@RestController
@RequestMapping("/user")
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
        ResponseCookie cookie = generateCookie("token", jwtToken);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Sign in succesful");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String nickname = (String) requestBody.get("nickname");
        String password = (String) requestBody.get("password");

        String jwtToken = userService.registerNewUserAndGetJwtToken(name, nickname, password);
        ResponseCookie cookie = generateCookie("token", jwtToken);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Sign up succesful");
    }

    private ResponseCookie generateCookie(String key, String value) {
        ResponseCookie cookie = ResponseCookie.from(key, value)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(3600 * 24 * 7)
                .sameSite("Lax")
                .build();

        return cookie;
    }
}
