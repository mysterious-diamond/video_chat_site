package com.aarons.videochat.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarons.videochat.util.JwtUtils;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final JwtUtils jwtUtils;

    public RoomController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @GetMapping()
    public String test(@CookieValue(name = "token", defaultValue = "") String token) {
        this.jwtUtils.decodeToken(token);
        return "Nice";
    }
}
