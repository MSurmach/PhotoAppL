package com.udemy.users.service;

import com.udemy.users.model.CreateUserRequestModel;
import com.udemy.users.model.User;

public interface UserService {
    User create(CreateUserRequestModel requestModel);
}
