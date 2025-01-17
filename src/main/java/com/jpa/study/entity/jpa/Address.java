package com.jpa.study.entity.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jpa.study.entity.jpa.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_SEQ")
    private Long seq;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_SEQ", nullable = false)
    private User user;

    @Column(name = "ZIPCODE", nullable = false)
    private int zipcode;

    @Column(name = "ADDRESS1", nullable = false)
    private String address1;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "NAME")
    private String name;


    @Builder
    public Address(int zipcode, String address1, User user) {
        this.zipcode = zipcode;
        this.address1 = address1;
        this.user = user;
    }
}
