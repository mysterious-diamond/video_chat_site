package com.aarons.videochat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/signin")
    public String signin() {
        return "Running!";
    }

    @GetMapping("/api/signup")
    public String signup() {
        return "Running!";
    }
}
