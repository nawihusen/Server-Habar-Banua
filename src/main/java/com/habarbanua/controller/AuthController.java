package com.habarbanua.controller;

import com.habarbanua.entity.User;
import com.habarbanua.model.LoginRegisterRequest;
import com.habarbanua.model.Response;
import com.habarbanua.model.TokenResponse;
import com.habarbanua.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/auth",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<TokenResponse> login(@RequestBody LoginRegisterRequest request){
        var response = authService.login(request);
        return Response.<TokenResponse>builder().data(response).build();
    }

    @DeleteMapping(path = "/auth")
    public Response<String> logout(User user){
        authService.logout(user);
        return Response.<String>builder().data("Success").build();
    }
}
