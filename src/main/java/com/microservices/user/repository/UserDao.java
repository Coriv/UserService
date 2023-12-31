package com.microservices.user.repository;

import com.microservices.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();
    @Override
    User save(User user);
}
