package com.codestar.HAMI.service;

import com.codestar.HAMI.entity.User;
import com.codestar.HAMI.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User user) {
        String password = user.getPassword();
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(password));
        System.out.println(user.getPassword());
        userService.addUser(user);
        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }

}
