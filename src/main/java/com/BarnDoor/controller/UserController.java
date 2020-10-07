package com.BarnDoor.controller;

import com.BarnDoor.model.ResponseDTO;
import com.BarnDoor.model.User;
import com.BarnDoor.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    UserLoginService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/userlogin")
    public ResponseDTO createUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ResponseDTO response=  userService.saveUsers(user);
        return response;
    }

    @GetMapping("/users")
    public String getUser(){

        return  bCryptPasswordEncoder.encode("sanket30");
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody User userRequest) {
        ResponseDTO response = userService.login(userRequest);
        return response;

    }

    @GetMapping("/logout")
    public ResponseDTO logout() {
        ResponseDTO response = userService.logout();
        return response;

    }

}
