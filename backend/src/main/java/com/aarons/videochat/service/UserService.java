package com.aarons.videochat.service;

import org.springframework.stereotype.Service;

import com.aarons.videochat.entity.User;
import com.aarons.videochat.error.UnauthorizedException;
import com.aarons.videochat.repository.UserRepository;
import com.aarons.videochat.util.JwtUtils;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public String validateUserAndGetJwtToken(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UnauthorizedException("User name or password is incorrect");
        }

        boolean isCorrectPassword = user.verifyPassword(password);
        if (!isCorrectPassword) {
            throw new UnauthorizedException("User name or password is incorrect");
        }

        return generateJwtToken(user);
    }

    public String registerNewUserAndGetJwtToken(String name, String nickname, String password) {
        User user = new User(name, nickname, password);
        userRepository.save(user);

        return generateJwtToken(user);
    }

    private String generateJwtToken(User user) {
        String nameToBeReturned = (user.getNickname() == null) ? user.getName() : user.getNickname();
        String jwtToken = jwtUtils.generateJwtToken(user.getId(), nameToBeReturned);
        return jwtToken;
    }
}
