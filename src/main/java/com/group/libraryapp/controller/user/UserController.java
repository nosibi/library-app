package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.response.UserResponse;
import com.group.libraryapp.dto.user.UserCreateRequest;
import com.group.libraryapp.dto.user.UserUpdateRequest;
import com.group.libraryapp.service.UserServiceV1;
import com.group.libraryapp.service.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userService;
    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    @PostMapping("/user") //사용자 등록
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUser(){
        return userService.getUser();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }

}
