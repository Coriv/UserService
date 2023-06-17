package com.microservices.user.mapper;

import com.microservices.user.dto.UserDto;
import com.microservices.user.entity.User;
import com.microservices.user.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserDao userDao;

    public User mapToUser(UserDto userDto) {
        Long id = userDto.getId();
        User user = id != null ?
                userDao.findById(id).orElse(new User()) : new User();

        user.setId(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setIdNumber(userDto.getIdNumber());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .idNumber(user.getIdNumber())
                .dateOfJoin(user.getDateOfJoin())
                .build();
    }

    public List<UserDto> mapToUserDtoList(List<User> usersList) {
        return usersList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
