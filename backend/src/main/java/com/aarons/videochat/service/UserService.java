package com.aarons.videochat.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aarons.videochat.entity.User;
import com.aarons.videochat.error.BadRequestException;
import com.aarons.videochat.error.UnauthorizedException;
import com.aarons.videochat.repository.UserRepository;
import com.aarons.videochat.util.JwtUtils;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = new JwtUtils();
    }

    public String validateUserAndGetJwtToken(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UnauthorizedException("User name or password is incorrect");
        }

        boolean isCorrectPassword = this.verifyPassword(password, user.getPassword());
        if (!isCorrectPassword) {
            throw new UnauthorizedException("User name or password is incorrect");
        }

        return generateJwtToken(user);
    }

    public String registerNewUserAndGetJwtToken(String name, String nickname, String password) {
        User user = this.createUser(name, nickname, password);
        userRepository.save(user);

        return generateJwtToken(user);
    }

    public User createUser(String name, String nickname, String password) {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("User name field is empty");
        } else if (password == null || name.isEmpty()) {
            throw new BadRequestException("User password field is empty");
        } else if (name.length() <= 4) {
            throw new BadRequestException("User name have more than 4 characters");
        }

        String hashedPassword = passwordEncoder.encode(password);
        return new User(name, nickname, hashedPassword);
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return this.passwordEncoder.matches(password, hashedPassword);
    }

    private String generateJwtToken(User user) {
        String nameToBeReturned = (user.getNickname() == null) ? user.getName() : user.getNickname();
        String jwtToken = jwtUtils.generateJwtToken(user.getId(), nameToBeReturned);
        return jwtToken;
    }
}
