package com.habarbanua.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
@EntityListeners({AuditingEntityListener.class})
public class Owner {
    private String name;

    private String summary;

    private String address;

    private String phone;

    private String email;

    private String status;

    private String education;

    private String linkedId;

    private String github;

    private String cv;
}
