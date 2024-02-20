package com.arianit.cityguideKosovo.service;
import com.arianit.cityguideKosovo.dto.UserDto;
import com.arianit.cityguideKosovo.entity.User;
import com.arianit.cityguideKosovo.exception.ResourceNotFoundException;
import com.arianit.cityguideKosovo.mapper.UserMapper;
import com.arianit.cityguideKosovo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapDtoToEntity(userDto);
        User savedUser = userRepository.save(user);

        return userMapper.mapEntityToDto(savedUser);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapEntityToDto)
                .toList();
    }

    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return userMapper.mapEntityToDto(user);
    }

    public void deleteUser(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    public void updatePassword(long userId, String password) {
        userRepository.findById(userId)
                .ifPresent(user -> {
                    user.setPassword(password);
                    userRepository.save(user);
                });
    }

    public void updateEmail(long userId, String email) {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Email already in use: " + email);
        }

        userRepository.findById(userId)
                .ifPresent(user -> {
                    user.setEmail(email);
                    userRepository.save(user);
                });
    }
}