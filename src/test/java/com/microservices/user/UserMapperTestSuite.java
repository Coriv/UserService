package com.microservices.user;

import com.microservices.user.dto.UserDto;
import com.microservices.user.entity.User;
import com.microservices.user.mapper.UserMapper;
import com.microservices.user.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;
    @MockBean
    private UserDao userDao;
    @Test
    public void testMapToUser() {
        // given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstName("Sebastian")
                .lastName("Brown")
                .idNumber("1234567890")
                .email("test@test.com")
                .build();
        when(userDao.findById(anyLong())).thenReturn(Optional.of(new User()));
        // when
        User result = userMapper.mapToUser(userDto);
        // then
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getFirstName(), result.getFirstName());
        assertEquals(userDto.getLastName(), result.getLastName());
        assertEquals(userDto.getIdNumber(), result.getIdNumber());
        assertEquals(userDto.getEmail(), result.getEmail());
    }

    @Test
    public void testMapToUserDto() {
        // given
        User user = new User();
        user.setId(1L);
        user.setFirstName("Sebastian");
        user.setLastName("Brown");
        user.setIdNumber("1234567890");
        user.setEmail("test@test.com");
        // when
        UserDto result = userMapper.mapToUserDto(user);
        // then
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getIdNumber(), result.getIdNumber());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void testMapToUserDtoList() {
        // given
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Sebastian");
        user1.setLastName("Brown");
        user1.setIdNumber("1234567890");
        user1.setEmail("test@test.com");
        userList.add(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Justyna");
        user2.setLastName("Smith");
        user2.setIdNumber("0987654321");
        user2.setEmail("test2@example.com");
        userList.add(user2);
        // when
        List<UserDto> result = userMapper.mapToUserDtoList(userList);

        // then
        assertEquals(2, result.size());

        UserDto userDto1 = result.get(0);
        assertEquals(user1.getId(), userDto1.getId());
        assertEquals(user1.getFirstName(), userDto1.getFirstName());
        assertEquals(user1.getLastName(), userDto1.getLastName());
        assertEquals(user1.getIdNumber(), userDto1.getIdNumber());
        assertEquals(user1.getEmail(), userDto1.getEmail());

        UserDto userDto2 = result.get(1);
        assertEquals(user2.getId(), userDto2.getId());
        assertEquals(user2.getFirstName(), userDto2.getFirstName());
        assertEquals(user2.getLastName(), userDto2.getLastName());
        assertEquals(user2.getIdNumber(), userDto2.getIdNumber());
        assertEquals(user2.getEmail(), userDto2.getEmail());
    }


}
