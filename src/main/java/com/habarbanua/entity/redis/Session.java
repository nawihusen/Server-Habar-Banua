package com.habarbanua.entity.redis;

import com.habarbanua.entity.New;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("session")
public class Session {

    @Indexed
    private String token;

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String role;

    @Column(name = "expired_at")
    private Long expiredAt;
}
