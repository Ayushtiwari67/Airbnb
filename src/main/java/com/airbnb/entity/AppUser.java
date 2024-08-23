package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false,length = 50)
    private String name;
    @Column(name = "email",length = 50, nullable = false)
    private String email;
    @Column(name = "username", unique = true, length = 50, nullable = false)
    private String username;
    @Column(name = "password", length = 10000, nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;

}
