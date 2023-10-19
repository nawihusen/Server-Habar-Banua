package com.habarbanua.entity;

import jakarta.persistence.*;
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
@Table(name = "projects")
@EntityListeners({AuditingEntityListener.class})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "name")
    private Portfolio owner;

    private String name;

    private String company;

    private String summary;

    @Column(name = "tech_stack")
    private String techStack;

    private String github;
}
