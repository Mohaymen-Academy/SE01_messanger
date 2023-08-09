package com.codestar.HAMI.service;

import com.codestar.HAMI.entity.User;
import com.codestar.HAMI.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void addUser(User user) {
        user.setPassword(getHashedPassword(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    private String getHashedPassword(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }

    public boolean authenticateUser(User user, String password) {
        return user.getPassword().equals(getHashedPassword(password));
    }
}
