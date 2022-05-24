package com.udemy.users.controller;

import com.udemy.users.model.CreateUserRequestModel;
import com.udemy.users.model.User;
import com.udemy.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public User create(@Valid @RequestBody CreateUserRequestModel requestModel) {
        return userService.create(requestModel);
    }
}
