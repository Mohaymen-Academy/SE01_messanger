package com.codestar.HAMI.controller;

import com.codestar.HAMI.entity.User;
import com.codestar.HAMI.model.AuthenticationResponse;
import com.codestar.HAMI.service.AuthenticationService;
import com.codestar.HAMI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/user")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> userSignup(@RequestBody User user){
        System.out.println(user);
        return ResponseEntity.ok(authenticationService.register(user));
    }

//    @PostMapping("/signin")
//    public void userSignIn(@Valid @RequestBody User user){
//        String
//
//        String password = user.getPassword();
//        user = userService.getUserByEmail(user.getEmail());
//        if (user == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found by email");
//        }
//        if (!userService.authenticateUser(user, password)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
//        }
//    }
}
