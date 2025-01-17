package com.jpa.study;

import com.jpa.study.entity.jpa.Address;
import com.jpa.study.entity.jpa.User;
import com.jpa.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    EntityManager em;

    public void test() {
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
        Pageable pageable = PageRequest.of(0, 3);

        List<User> userList = userRepository.getUserList(pageable);
        userList.forEach(System.out::println);
    }
}
