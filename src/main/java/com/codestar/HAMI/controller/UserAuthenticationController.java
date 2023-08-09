package com.codestar.HAMI.controller;

import com.codestar.HAMI.entity.User;
import com.codestar.HAMI.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/user")
public class UserAuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public void userSignup(@Valid @RequestBody User user){
        authenticationService.addUser(user);
    }

    @GetMapping("/signin")
    public void userSignin(@Valid @RequestBody User user){
        String password = user.getPassword();
        user = authenticationService.getUserByEmail(user.getEmail());
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found by email");
        }
        if (!authenticationService.authenticateUser(user, password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }
    }
}
