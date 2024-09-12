package com.group.libraryapp.service;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.response.UserResponse;
import com.group.libraryapp.dto.user.UserCreateRequest;
import com.group.libraryapp.dto.user.UserUpdateRequest;
import com.group.libraryapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(),request.getAge()));
    }
    @Transactional(readOnly = true)
    public List<UserResponse> getUser(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(),user.getName(),user.getAge())).collect(Collectors.toList());
    }
    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
        userRepository.save(user);
    }
    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name);
        if(user == null){
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}
