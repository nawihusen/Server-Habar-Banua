package com.habarbanua.service.auth;

import com.habarbanua.entity.User;
import com.habarbanua.entity.redis.Session;
import com.habarbanua.model.LoginRegisterRequest;
import com.habarbanua.model.TokenResponse;
import com.habarbanua.repository.mysql.UserRepository;
import com.habarbanua.repository.redis.UserRedis;
import com.habarbanua.security.BCrypt;
import com.habarbanua.service.ValidationService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserRedis userRedis;

    @Autowired
    private ValidationService validationService;


    @Override
    @Transactional
    public TokenResponse login(LoginRegisterRequest request) {
        validationService.validate(request);

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password is wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())){
            var token = UUID.randomUUID().toString();
            var expired = generateExpired();
            user.setToken(token);
            user.setExpiredAt(expired);
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(token)
                    .expiredAt(expired).build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password is wrong");
        }
    }

    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setExpiredAt(null);

        userRepository.save(user);
    }

    private Long generateExpired(){
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }

}
