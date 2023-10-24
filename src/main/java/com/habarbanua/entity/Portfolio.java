package com.habarbanua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portfolio")
@EntityListeners({AuditingEntityListener.class})
public class Portfolio {

    @Id
    private String name;

    private String summary;

    private String address;

    private String phone;

    private String email;

    private String status;

    @Column(name = "linked_in")
    private String linkedIn;

    private String github;

    private String cv;

    @OneToMany(mappedBy = "owner")
    private String education;

    @OneToMany(mappedBy = "owner")
    private List<Experience> experiences;

    @OneToMany(mappedBy = "owner")
    private List<Project> projects;
}
