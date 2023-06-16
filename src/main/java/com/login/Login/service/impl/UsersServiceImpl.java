package com.login.Login.service.impl;

import com.login.Login.Repository.UsersRepository;
import com.login.Login.entities.Users;
import com.login.Login.request.dto.LoginDto;
import com.login.Login.request.dto.RegisterDto;
import com.login.Login.service.UsersService;
import com.login.Login.utils.EmailUtil;
import com.login.Login.utils.OtpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public String regenerateOtp(String email) {
        //Users users = usersRepository.getByEmail(email);
        String otp = otpUtil.generateOtp();
        emailUtil.sendOtpEmail(email, otp);
        return otp;

    }

    @Override
    public void saveUser(RegisterDto registerDto) {
        Users users = new Users();
        BeanUtils.copyProperties(registerDto, users);
        String otp=otpUtil.generateOtp();
        emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
        users.setOtp(otp);
        users.setOtpGeneratedTime(LocalDateTime.now());
        usersRepository.save(users);

    }
@Override
    public void verifyAccount(String email, String otp) {
        Users users = usersRepository.getByEmail(email);
        if (users.getOtp().equals(otp) && Duration.between(users.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            users.setActive(true);
        }
    usersRepository.save(users);
    }

//

    @Override
    public boolean existsByName(String name) {
        return usersRepository.existsByName(name);
    }


    @Override
    public boolean findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

//    @Override
//    public boolean login(LoginDto loginDto) {
//        Users users=usersRepository.getByEmail(loginDto.getEmail());
//        if(users.getPassword()==loginDto.getPassword())
//        {
//            return true;
//        }
//        else
//            return false;
//
//    }

    @Override
    public boolean isActive(String email) {
        Users users=usersRepository.getByEmail(email);
        if(users.isActive())
            return true;
        else
            return false;

    }

//    @Override
//    public void login(LoginDto loginDto) {
//        Users users = usersRepository.getByName(loginDto.getUsername());
//        if (users.getPassword().equals(loginDto.getPassword())) {
//
//        }
//    }




//    @Override
//    public Users getByEmail(String email) {
//        return usersRepository.getByEmail(email);
//    }
//
////
////    @Override
////    public boolean isUpdateExistsByEmail(String email, Long id) {
////        return usersRepository.existsByEmailAndIdNot(email,id);
////    }



}
