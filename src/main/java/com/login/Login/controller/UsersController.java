package com.login.Login.controller;

import com.login.Login.common.response.BaseResponse;
import com.login.Login.request.dto.LoginDto;
import com.login.Login.request.dto.RegisterDto;
import com.login.Login.rest.enums.RequestStatus;
import com.login.Login.service.UsersService;
import com.login.Login.utils.EndpointURI;
import com.login.Login.utils.ValidationResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ValidationResponseCode validationResponseCode;

    @PostMapping(value = EndpointURI.USERREGISTER)
    public ResponseEntity<Object> saveUser(@RequestBody RegisterDto registerDto) {
//        if (usersService.existsByName(registerDto.getName())) {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
//                    validationResponseCode.getUserAlreadyExists(),
//                    validationResponseCode.getValidationUserAlreadyExists()));
//        }
//        if (usersService.existsByEmail(registerDto.getEmail())) {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
//                    validationResponseCode.getUserAlreadyExists(),
//                    validationResponseCode.getValidationUserAlreadyExists()));
//        }

        usersService.saveUser(registerDto);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getSaveUserSuccessMessage()));
    }
    @PostMapping(EndpointURI.USERLOGIN)
    public void loginUser(LoginDto loginDto) {

        usersService.loadUserByUsername(loginDto.getUsername());
    }


//    @PutMapping(value = EndpointURI.USERVERIFICATION)
//    public ResponseEntity<Object> verifyAccount(@PathVariable String email, @PathVariable String otp) {
//        if (!usersService.existsByEmail(email)) {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
//                    validationResponseCode.getUserNotExistsCode(),
//                    validationResponseCode.getUserNotExistsMessage()));
//        }
//       usersService.verifyAccount(email,otp);
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
//                    validationResponseCode.getCommonSuccessCode(),
//                    validationResponseCode.getUserVerifiedMessage()));
//    }
//    @GetMapping(value = EndpointURI.USERLOGIN)
//    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto)
//    {
//        if(!usersService.existsByEmail(loginDto.getEmail()))
//        {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
//                    validationResponseCode.getUserNotExistsCode(),
//                    validationResponseCode.getUserNotExistsMessage()));
//        }
//        if(!usersService.isActive(loginDto.getEmail()))
//        {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
//                    validationResponseCode.getFailureCode(),
//                    validationResponseCode.getUserNotVerifiedMessage()));
//        }
//        if(!usersService.login(loginDto))
//        {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
//                    validationResponseCode.getFailureCode(),
//                    validationResponseCode.getUserLoginFailedMessage()));
//        }
//
//        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
//                validationResponseCode.getCommonSuccessCode(),
//                validationResponseCode.getUserLoginSuccessMessage()));
//    }


}



