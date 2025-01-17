package com.jpa.study.entity.jpa;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEAM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_SEQ")
    private Long seq;

    @OneToMany(mappedBy = "team")
    private List<User> userList;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public Team(String name) {
        this.name = name;
    }

}
