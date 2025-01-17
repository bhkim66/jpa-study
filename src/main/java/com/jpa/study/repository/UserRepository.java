package com.jpa.study.repository;

import com.jpa.study.dto.UserDto;
import com.jpa.study.entity.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select distinct u from User u join u.addresses a join fetch u.team t where t.name = '머스탱1'")
    List<User> getUserList(Pageable pageable);

    @Query(value = "select u.age " +
            "from USERS u " +
            "join ADDRESS a on u.user_seq = a.user_seq " +
            "left join TEAM t on t.team_seq = u.team_seq " +
            "group by u.age"
    , nativeQuery = true)
    List<Integer> getUserAgeMax();


    @Query("select distinct new com.jpa.study.dto.UserDto(u.seq, u.id, u.name, u.age, t.name) from User u join u.addresses a join u.team t")
    Page<UserDto> getUserDto(Pageable pageable);

    @Query(value = "select distinct u from User u join u.addresses a left join fetch u.team t "
    , countQuery = "select count(u) from User u left join u.team t")
    Page<User> getUserPage(Pageable pageable);

}
