package com.microservices.user.service;

import com.microservices.user.entity.User;
import com.microservices.user.exception.InvalidUserIdException;
import com.microservices.user.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User findUserById(Long userId) throws InvalidUserIdException {
        return userDao.findById(userId).orElseThrow(InvalidUserIdException::new);
    }

    public User registerUser(User user) {
        user.setDateOfJoin(LocalDateTime.now());
        return userDao.save(user);
    }

    public User updateUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(Long id) throws InvalidUserIdException {
        User user = userDao.findById(id).orElseThrow(InvalidUserIdException::new);
        //todo check if wallet of user is empty
        userDao.deleteById(id);
    }
}
