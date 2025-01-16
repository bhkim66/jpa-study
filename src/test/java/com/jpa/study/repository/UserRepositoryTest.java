package com.jpa.study.repository;

import com.jpa.study.entity.jpa.Address;
import com.jpa.study.entity.jpa.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
@EnableJpaAuditing
public class UserRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 address 가져오기")
    void 멤버_주소_가져오기() {
        //given
        for(int i = 0; i < 5; i++) {
            User user = User.builder()
                    .id("bhkim62" + i)
                    .name("김병호" + i)
                    .password("test1234")
                    .age(30)
                    .phoneNumber(null)
                    .build();
            em.persist(user);
            Address address = Address.builder()
                    .zipcode(12345 + i)
                    .address1("서울시입니다" + i)
                    .user(user)
                    .build();
            em.persist(address);
            Address address2 = Address.builder()
                    .zipcode(32345 + i)
                    .address1("부산시입니다" + i)
                    .user(user)
                    .build();
            em.persist(address2);
        }
        em.clear();

        Pageable pageable = PageRequest.of(0, 3);

        List<User> userList = userRepository.getUserList(pageable);
        userList.forEach(System.out::println);

    }

}
