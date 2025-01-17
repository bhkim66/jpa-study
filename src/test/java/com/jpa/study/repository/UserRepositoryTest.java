package com.jpa.study.repository;

import com.jpa.study.dto.UserDto;
import com.jpa.study.entity.jpa.Address;
import com.jpa.study.entity.jpa.Team;
import com.jpa.study.entity.jpa.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
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
            Team team = Team.builder()
                        .name("머스탱" + i)
                        .build();
            em.persist(team);
            User user = User.builder()
                    .id("bhkim62" + i)
                    .name("김병호" + i)
                    .password("test1234")
                    .age(30 + i)
                    .phoneNumber(null)
                    .team(i % 2 == 0 ? team : null)
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


        User user = userRepository.findById(1L).orElseThrow();
        System.out.println("user = " + user);

        Pageable pageable = PageRequest.of(0, 3);

        List<User> userList = userRepository.getUserList(pageable);
        userList.forEach(System.out::println);
        List<Integer> userAgeMax = userRepository.getUserAgeMax();
        System.out.println("ageMax = " + userAgeMax);
//        Page<UserDto> userDto = userRepository.getUserDto(pageable);
//        log.info("userDto : {}" , userDto);
//        log.info("userDto : {}" , userDto.getContent());
        Page<User> userPage = userRepository.getUserPage(pageable);
        log.info("userPage : {}" , userPage);
        log.info("userPage : {}" , userPage.getContent());
    }
}
