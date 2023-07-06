package com.login.Login.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String active;
    private String otp;
    private LocalDateTime otpGeneratedTime;
//    @ManyToMany
//    @JoinColumn(name = "role_id",nullable = false)
//    private Set<Roles> roles=new HashSet<>();

}
