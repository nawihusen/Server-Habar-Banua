package com.habarbanua.service.auth;

import com.habarbanua.entity.User;
import com.habarbanua.model.LoginRegisterRequest;
import com.habarbanua.model.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginRegisterRequest request);

    void logout(User user);

}
