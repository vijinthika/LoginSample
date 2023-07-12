package com.login.Login.service.impl;

import com.login.Login.Repository.EmailRepository;
import com.login.Login.Repository.UsersRepository;
import com.login.Login.entities.Email;
import com.login.Login.entities.Users;
import com.login.Login.request.dto.EmailDto;
import com.login.Login.request.dto.LoginDto;
import com.login.Login.request.dto.RegisterDto;
import com.login.Login.rest.enums.ActiveStatus;
import com.login.Login.service.UsersService;
import com.login.Login.utils.EmailUtil;
import com.login.Login.utils.OtpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Users user = usersRepository.findByEmail(email);
//        if(user == null) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Roles> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//    }
//    @Override
//    public String regenerateOtp(String email) {
//        //Users users = usersRepository.getByEmail(email);
//        String otp = otpUtil.generateOtp();
//        emailUtil.sendOtpEmail(email, otp);
//        return otp;
//    }

    @Override
    public void saveUser(RegisterDto registerDto) {
        Users users = new Users();
        users.setActive(ActiveStatus.NEW.getStatus());
//        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        BeanUtils.copyProperties(registerDto, users);
        String otp = otpUtil.generateOtp();
        emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
        users.setOtp(otp);
        users.setOtpGeneratedTime(LocalDateTime.now());
        usersRepository.save(users);
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return usersRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public String getActiveStatus(String username) {
        return usersRepository.findByNameIgnoreCase(username).getActive();
    }

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return usersRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public void verifyAccount(String name) {
        Users users = usersRepository.findByNameIgnoreCase(name);
        users.setActive(ActiveStatus.VERIFIED.getStatus());
        usersRepository.save(users);
    }

    @Override
    public boolean login(LoginDto loginDto) {
        Users users = usersRepository.findByNameIgnoreCase(loginDto.getUsername());
        if (users.getPassword().equals(loginDto.getPassword())) return true;
        return false;
    }

    @Override
    public boolean isActive(String name) {
        Users users = usersRepository.findByNameIgnoreCase(name);
        return users.getActive().equals(ActiveStatus.VERIFIED.getStatus());
    }

    @Override
    public void generateOtp(String username) {
        Users users = usersRepository.findByNameIgnoreCase(username);
        String otp = otpUtil.generateOtp();
        emailUtil.sendOtpEmail(users.getEmail(), otp);
        users.setOtp(otp);
        users.setOtpGeneratedTime(LocalDateTime.now());
        usersRepository.save(users);
    }

    public boolean checkTimeLimit(String otp, String username) {
        Users users = usersRepository.findByNameIgnoreCase(username);
        if (users.getOtp().equals(otp) && Duration.between(users.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            return true;
        }
        return false;
    }

    @Override
    public void recreatePassword(String username, String password, String otp) {
        Users users = usersRepository.findByNameIgnoreCase(username);
        users.setPassword(password);
        usersRepository.save(users);
    }

    @Override
    public void deActivateAccount(String username) {
        Users users = usersRepository.findByNameIgnoreCase(username);
        users.setActive(ActiveStatus.DEACTIVE.getStatus());
        usersRepository.save(users);
    }

    @Override
    public void sendEmailWithAttachment(EmailDto emailDto) {
        try {
            byte[] attachmentContent = getFileContent(emailDto.getAttachment()); // Implement this method to read file content
            String attachmentFilename = getFilename(emailDto.getAttachment()); // Implement this method to extract the filename from the file path
            emailUtil.sendEmailWithAttachment(emailDto, attachmentContent, attachmentFilename);
            // ...
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content. Please try again.", e);
        }
    }
    @Override
    public void sendEmailWithAttachmentFile(EmailDto emailDto) {
        try {
            byte[] attachmentContent = getFileContent(emailDto.getAttachment());
            String attachmentFilename = getFilename(emailDto.getAttachment());
            emailUtil.sendEmailWithAttachment(emailDto, attachmentContent, attachmentFilename);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content. Please try again.", e);
        }
    }
    private byte[] getFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
    private String getFilename(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

//    @Override
//    public boolean findByEmail(String email) {
//        return usersRepository.findByEmail(email);
//    }
//
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
//
//    @Override
//    public boolean isUpdateExistsByEmail(String email, Long id) {
//        return usersRepository.existsByEmailAndIdNot(email,id);
//    }
}
