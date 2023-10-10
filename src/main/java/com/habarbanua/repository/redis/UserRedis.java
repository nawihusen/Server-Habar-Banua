package com.habarbanua.repository.redis;

import com.habarbanua.entity.User;
import com.habarbanua.entity.redis.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRedis extends CrudRepository<Session, String> {

    Optional<Session> findByToken(String token);

    Optional<Session> findOne(String token);
}
