package com.jpa.study.repository;

import com.jpa.study.entity.jpa.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.addresses where u.seq = 1")
    List<User> getUserList(Pageable pageable);
}
