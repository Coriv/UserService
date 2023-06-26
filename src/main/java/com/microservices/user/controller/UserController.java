package com.microservices.user.controller;

import com.microservices.user.dto.AuthenticationDto;
import com.microservices.user.dto.UserDto;
import com.microservices.user.entity.User;
import com.microservices.user.exception.InvalidUserIdException;
import com.microservices.user.feign.AuthClient;
import com.microservices.user.feign.WalletClient;
import com.microservices.user.feign.WalletCryptoClient;
import com.microservices.user.mapper.UserMapper;
import com.microservices.user.service.AmqpService;
import com.microservices.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthClient authClient;
    private final WalletClient walletClient;
    private final WalletCryptoClient walletCryptoClient;
    private final AmqpService amqpService;
    @GetMapping
    public ResponseEntity<List<UserDto>> fetchAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(usersList));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> fetchUserById(@PathVariable Long userId) throws InvalidUserIdException {
        var user = userService.findUserById(userId);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {

        var user = userMapper.mapToUser(userDto);
        var savedUser = userService.registerUser(user);
        var id = savedUser.getId();
        var authDto = AuthenticationDto.builder()
                .userId(id)
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        var token = authClient.registerUserAuth(authDto);
        walletClient.createWalletForNewUser(id);
        walletCryptoClient.createCryptoWallets(id);
        amqpService.notifyNewUserByEmail(savedUser.getEmail());
        return ResponseEntity.ok(token);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUserData(@Valid @RequestBody UserDto userDto) {
        var user = userMapper.mapToUser(userDto);
        var savedUser = userService.updateUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam Long userId) throws InvalidUserIdException {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
