package com.udemy.users.data.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CreateUserRequestModel implements Serializable {
    @NotEmpty(message = "The first name can't be empty")
    private String firstName;
    @NotEmpty(message = "The last name can't be empty")
    private String lastName;
    @Email(message = "The email is not valid")
    private String email;
    @Size(min = 8, max = 16, message = "The password length can be between 8 and 16 characters")
    private String password;
}
