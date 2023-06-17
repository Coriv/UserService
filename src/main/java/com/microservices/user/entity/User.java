package com.microservices.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Users", indexes = @Index(columnList = "id"))
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2)
    @NotNull
    private String firstName;

    @Size(min = 2)
    @NotNull
    private String lastName;

    @Column(length = 11)
    @Size(min = 11, max = 11, message = "Pesel have to has exactly 11 numbers")
    private String idNumber;

    @Column(updatable = false)
    private LocalDateTime dateOfJoin;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;
}