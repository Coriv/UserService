package com.microservices.user;

import com.microservices.user.entity.User;
import com.microservices.user.exception.InvalidUserIdException;
import com.microservices.user.repository.UserDao;
import com.microservices.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestSuite {

    @InjectMocks
    private UserService service;
    @Mock
    private UserDao userDao;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("Sebastian");
        user.setLastName("Brown");
        user.setEmail("test@test.com");
        user.setIdNumber("12345678901");
    }

    @Test
    public void testGetAllUsers() {
        // given
        List<User> userList = new ArrayList<>();
        userList.add(user);
        User user2 = new User();
        user2.setFirstName("Justyna");
        user2.setLastName("Crud");
        user2.setEmail("test2@test2.com");
        user2.setIdNumber("098765434312");
        userList.add(user2);

        when(userDao.findAll()).thenReturn(userList);
        // when
        List<User> result = service.getAllUsers();
        // then
        assertEquals(2, result.size());
        assertEquals(userList, result);
    }

    @Test
    public void testFindUserById() throws InvalidUserIdException {
        // given
        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));
        // when
        User result = service.findUserById(user.getId());
        // then
        assertEquals(user, result);
    }

    @Test
    public void testRegisterUser() {
        // given
        when(userDao.save(any(User.class))).thenReturn(user);
        // when
        User result = service.registerUser(user);
        // then
        assertNotNull(result.getId());
        assertEquals(user.getEmail(), result.getEmail());
        assertNotNull(result.getDateOfJoin());
    }

    @Test
    public void testUpdateUser() {
        // given
        when(userDao.save(user)).thenReturn(user);
        // when
        User result = service.updateUser(user);
        // then
        assertEquals(user, result);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() throws InvalidUserIdException {
        // given
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));
        // when
        service.deleteUser(user.getId());
        // then
        verify(userDao, times(1)).deleteById(user.getId());
    }

}
