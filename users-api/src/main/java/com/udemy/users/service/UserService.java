package com.udemy.users.service;

import com.udemy.users.data.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDTO create(UserDTO requestModel);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDTO getUserByEmail(String email);
}
