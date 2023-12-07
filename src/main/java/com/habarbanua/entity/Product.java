package com.habarbanua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "products")
//@EntityListeners({AuditingEntityListener.class})
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Long userId;
//
//    private Long shopId;
//
//    private String name;
//
//    private Long sold;
//
//    private Integer rating;
//
//    private String comment;
//
//    private String description;
//
//    private String photo;
//
//    private Long stock;
//
//    private Long price;
//
//    @CreatedDate
//    @Column(name = "created_at")
//    private Instant createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private Instant updatedAt;
//
//    @Column(name = "deleted_at")
//    private Instant deletedAt;
//}
