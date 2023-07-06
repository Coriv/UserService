package com.microservices.user;

import com.microservices.user.entity.User;
import com.microservices.user.repository.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDaoTestSuite {
    @Autowired
    private UserDao userDao;

    @Test
    void saveUserAndFindByIdTest() {
        //given
        User user = new User();
        user.setEmail("email@test.com");
        user.setFirstName("Sebastian");
        user.setLastName("Brown");
        user.setIdNumber("12345678912");
        user.setDateOfJoin(LocalDateTime.now());
        //when
        userDao.save(user);
        var savedUser = userDao.findById(user.getId())
                .orElseThrow();
        //then
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getDateOfJoin(), savedUser.getDateOfJoin());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getLastName(), savedUser.getLastName());
        //cleanUp
        userDao.deleteById(savedUser.getId());
    }
}
