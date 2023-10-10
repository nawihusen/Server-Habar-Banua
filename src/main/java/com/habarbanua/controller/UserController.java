package com.habarbanua.controller;

import com.habarbanua.entity.User;
import com.habarbanua.model.*;
import com.habarbanua.model.user.UserResponse;
import com.habarbanua.model.user.UserUpdateRequest;
import com.habarbanua.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> register(@RequestBody LoginRegisterRequest request){
        userService.register(request);
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/users/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<UserResponse> getProfile(User user){
        var userResponse = userService.getProfile(user);
        return Response.<UserResponse>builder().data(userResponse).build();
    }

    @PatchMapping(
            path = "/users/profile",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<UserResponse> editProfile(@RequestBody UserUpdateRequest request, User user){
        var userResponse = userService.editProfile(request, user);
        return Response.<UserResponse>builder().data(userResponse).build();
    }

    @GetMapping(path = "/users")
    public Response<UserResponse> getOtherProfile(User user, @RequestParam String username){
        var userResponse = userService.getOtherProfile(user, username);
        return Response.<UserResponse>builder().data(userResponse).build();
    }

    @GetMapping(path = "/test", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String testRedis(@RequestBody Test request){
        var str = userService.testRedis(request);
        return "";
    }
}
