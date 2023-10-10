package com.habarbanua.service.user;

import com.habarbanua.entity.User;
import com.habarbanua.model.LoginRegisterRequest;
import com.habarbanua.model.Test;
import com.habarbanua.model.user.UserResponse;
import com.habarbanua.model.user.UserUpdateRequest;

public interface UserService {

    void register(LoginRegisterRequest registerRequest);

    UserResponse getProfile(User user);

    String testRedis(Test t);

    UserResponse editProfile(UserUpdateRequest request, User user);

    UserResponse getOtherProfile(User user, String username);

}
