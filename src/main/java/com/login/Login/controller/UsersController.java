package com.login.Login.controller;

import com.login.Login.common.response.BaseResponse;
import com.login.Login.request.dto.RegisterDto;
import com.login.Login.rest.enums.RequestStatus;
import com.login.Login.service.UsersService;
import com.login.Login.utils.EndpointURI;
import com.login.Login.utils.ValidationResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ValidationResponseCode validationResponseCode;

    @PostMapping(value = EndpointURI.USERREGISTER)
    public ResponseEntity<Object> saveUser(@RequestBody RegisterDto registerDto)
    {
        if(usersService.existsByName(registerDto.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationResponseCode.getUserAlreadyExists(),
                    validationResponseCode.getValidationUserAlreadyExists()));
        }

        if(usersService.existsByEmail(registerDto.getEmail()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationResponseCode.getUserAlreadyExists(),
                    validationResponseCode.getValidationUserAlreadyExists()));
        }
    if(usersService.regenerateOtp(registerDto.getEmail())!=registerDto.getOtp())
    {
        return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                validationResponseCode.getFailureCode(),
                validationResponseCode.getSaveUserSuccessMessage()));
    }
        usersService.saveUser(registerDto);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getSaveUserSuccessMessage()));
    }
//    @PutMapping(value = EndpointURI.USERVERIFICATION)
//    public  ResponseEntity<Object> verifyAccount(@PathVariable String email,@PathVariable String otp)
//    {
//        if(!usersService.findByEmail(email))
//        {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
//                    validationResponseCode.getUserNotExistsCode(),
//                    validationResponseCode.getUserNotExistsMessage()));
//        }
//        if(usersService.verifyAccount(email,otp)==false)
//        {
//            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
//                    validationResponseCode.getFailureCode(),
//                    validationResponseCode.getUserVerificationFailedMessage()));
//        }
//        else
//        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
//              validationResponseCode.getCommonSuccessCode(),
//                validationResponseCode.getUserVerifiedMessage()));
//    }




}
