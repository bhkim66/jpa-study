package com.jpa.study.entity.jpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jpa.study.entity.jpa.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Entity
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SEQ")
    private Long seq;

    @Column(name = "ID", nullable = false, unique = true)
    private String id;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE", nullable = false)
    private int age;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

//    @JsonManagedReference
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

    @Builder
    public User(String id, String password, String name, int age, String phoneNumber, List<Address> addresses) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }
}
