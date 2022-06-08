package com.udemy.users.controller;

import com.udemy.users.data.model.CreateUserRequestModel;
import com.udemy.users.data.model.CreateUserResponseModel;
import com.udemy.users.data.shared.UserDTO;
import com.udemy.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${token.secret}")
    private String secretToken;

    @Autowired
    private UserService userService;

    @GetMapping("/isAvailable")
    public String check() {
        return "Success";
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working with token " + secretToken;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<CreateUserResponseModel> create(@Valid @RequestBody CreateUserRequestModel requestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDTO createdUser = userService.create(modelMapper.map(requestModel, UserDTO.class));
        CreateUserResponseModel response = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
