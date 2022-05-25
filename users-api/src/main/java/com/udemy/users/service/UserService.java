package com.udemy.users.service;

import com.udemy.users.data.shared.UserDTO;

public interface UserService {
    UserDTO create(UserDTO requestModel);
}
