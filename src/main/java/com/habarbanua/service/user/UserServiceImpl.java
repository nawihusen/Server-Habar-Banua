package com.habarbanua.service.user;

import com.habarbanua.entity.User;
import com.habarbanua.entity.redis.Session;
import com.habarbanua.model.LoginRegisterRequest;
import com.habarbanua.model.Test;
import com.habarbanua.model.user.UserResponse;
import com.habarbanua.model.user.UserUpdateRequest;
import com.habarbanua.repository.redis.UserRedis;
import com.habarbanua.repository.mysql.UserRepository;
import com.habarbanua.security.BCrypt;
import com.habarbanua.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private UserRedis userRedis;

    @Autowired
    private RedisTemplate<String, String> template;

    @Transactional
    public void register(LoginRegisterRequest request){
        validationService.validate(request);

        if (userRepository.existsByUsername(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already used");
        };

        var user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

        userRepository.save(user);
    }

    public UserResponse getProfile(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public UserResponse editProfile(UserUpdateRequest request, User user){
        // tambahkan validate jika ada

        if (Objects.nonNull(request.getUsername())){
            user.setUsername(request.getUsername());
        }
        if (Objects.nonNull(request.getName())){
            user.setName(request.getName());
        }
        if (Objects.nonNull(request.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }
        if (Objects.nonNull(request.getPhone())){
            user.setPhone(request.getPhone());
        }

        userRepository.save(user);

        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .role(user.getRole())
                .phone(user.getPhone())
                .build();
    }


    @Override
    public UserResponse getOtherProfile(User user, String username) {
        var data = userRepository.findByUsername(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found")
        );

        return toUserResponse(data);
    }

    @Override
    public String testRedis(Test t) {
        var session = new Session();
        String token = UUID.randomUUID().toString();
        session.setToken(token);
        session.setId(1L);
        session.setUsername("username");
        session.setPhone("phone");
        session.setName("nama");

        userRedis.save(session);

        Session temp = userRedis.findByToken(token).orElse(null);
        System.out.println(temp.toString());

        return "";
    }


    private UserResponse toUserResponse(User user){
        var res = new UserResponse();
        res.setName(user.getName());
        res.setUsername(user.getUsername());
        res.setRole(user.getRole());
        res.setPhone(user.getPhone());
        return res;
    }

}
