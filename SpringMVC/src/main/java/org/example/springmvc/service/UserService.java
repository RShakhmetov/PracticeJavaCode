package org.example.springmvc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springmvc.DTO.User;
import org.example.springmvc.global_handler.CustomException;
import org.example.springmvc.models.UserEntity;
import org.example.springmvc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new CustomException("User with id " + id + " does`t exist");
        } else {
            return userRepository.findById(id).get();
        }
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new CustomException("Repository is empty");
        } else {
            return userRepository.findAll();
        }
    }

    public UserEntity createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException("User already exists");
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            userRepository.save(newUser);
            return newUser;
        }
    }

    @Transactional
    public UserEntity updateUser(Long id, User user) {
        if (userRepository.findById(id).isPresent()) {
            UserEntity userEntity = userRepository.findById(id).get();
            userEntity.setEmail(user.getEmail());
            userEntity.setName(user.getName());
            userRepository.save(userEntity);
            return userEntity;
        } else {
            throw new CustomException("User with id " + id + " does`t exist");
        }
    }

    public UserEntity deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            UserEntity user = userRepository.findById(id).get();
            userRepository.deleteById(id);
            return user;
        } else {
            throw new CustomException("User with id " + id + " does`t exist");
        }
    }
}
