package com.login.Login.service;

import com.login.Login.entities.Users;
import com.login.Login.request.dto.LoginDto;
import com.login.Login.request.dto.RegisterDto;

public interface UsersService {
    void saveUser(RegisterDto registerDto);

    public boolean existsByName(String name);
    public String regenerateOtp(String email);

//    public boolean verifyAccount(String email,String otp);

    public boolean findByEmail(String email);
    public boolean existsByEmail(String email);

//    public void login(LoginDto loginDto);

}
