package com.udemy.users.service.impl;

import com.udemy.users.data.entity.UserEntity;
import com.udemy.users.data.shared.UserDTO;
import com.udemy.users.repository.UserRepository;
import com.udemy.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO create(UserDTO requestModel) {
        requestModel.setUserId(UUID.randomUUID().toString());
        requestModel.setEncryptedPassword(bCryptPasswordEncoder.encode(requestModel.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(requestModel, UserEntity.class);
        UserEntity saved = userRepository.save(userEntity);
        return modelMapper.map(saved, UserDTO.class);
    }
}
