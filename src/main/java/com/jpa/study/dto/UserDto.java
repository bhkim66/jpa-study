package com.jpa.study.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserDto {
    private Long seq;
    private String id;
    private String userName;
    private int age;
    private String teamName;

    public UserDto(Long seq, String id, String userName, int age, String teamName) {
        this.seq = seq;
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.teamName = teamName;
    }
}
