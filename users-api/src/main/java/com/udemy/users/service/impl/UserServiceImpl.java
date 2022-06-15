package com.udemy.users.service.impl;

import com.udemy.users.data.entity.UserEntity;
import com.udemy.users.data.model.AlbumResponseModel;
import com.udemy.users.data.shared.UserDTO;
import com.udemy.users.repository.UserRepository;
import com.udemy.users.service.AlbumsService;
import com.udemy.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AlbumsService albumsService;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if (Objects.isNull(userEntity)) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                Collections.emptyList());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) throw new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (Objects.isNull(userEntity)) throw new UsernameNotFoundException(userId);
        UserDTO userDTO = new ModelMapper().map(userEntity, UserDTO.class);
        List<AlbumResponseModel> albums = albumsService.getAlbums(userId);
        userDTO.setAlbums(albums);
        return userDTO;
    }
}
