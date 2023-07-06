package com.login.Login.controller;

import com.login.Login.common.response.BaseResponse;
import com.login.Login.request.dto.LoginDto;
import com.login.Login.request.dto.RegisterDto;
import com.login.Login.rest.enums.ActiveStatus;
import com.login.Login.rest.enums.RequestStatus;
import com.login.Login.service.UsersService;
import com.login.Login.utils.EndpointURI;
import com.login.Login.utils.ValidationResponseCode;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ValidationResponseCode validationResponseCode;

    @GetMapping("/hello")
    public String a()
    {
        return "hi";
    }

    @PostMapping(value = EndpointURI.USER_REGISTER)
    public ResponseEntity<Object> saveUser(@RequestBody RegisterDto registerDto) {
        if (usersService.existsByNameIgnoreCase(registerDto.getName()) ) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationResponseCode.getUserAlreadyExists(),
                    validationResponseCode.getValidationUserAlreadyExists()));
        }
        if (usersService.existsByEmailIgnoreCase(registerDto.getEmail())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationResponseCode.getUserAlreadyExists(),
                    validationResponseCode.getValidationUserAlreadyExists()));
        }

        usersService.saveUser(registerDto);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getSaveUserSuccessMessage()));

    }
    @PutMapping(value = EndpointURI.USER_VERIFICATION)
    public ResponseEntity<Object> verifyAccount(@PathVariable String username, @PathVariable String otp) {
        if (!usersService.existsByNameIgnoreCase(username)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getUserNotExistsCode(),
                    validationResponseCode.getUserNotExistsMessage()));
        }
        if (!usersService.checkTimeLimit(otp, username)) {
            usersService.generateOtp(username);
            return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                    validationResponseCode.getCommonSuccessCode(),
                    validationResponseCode.getTimeLimitExceedMessage()));
        }
        usersService.verifyAccount(username);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getUserVerifiedMessage()));
    }

    @GetMapping(value = EndpointURI.USER_LOGIN)
    public ResponseEntity<Object> userLogin(@RequestBody LoginDto loginDto)
    {
        if(!usersService.existsByNameIgnoreCase(loginDto.getUsername()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getUserNotExistsCode(),
                    validationResponseCode.getUserNotExistsMessage()));
        }
        if(!usersService.isActive(loginDto.getUsername()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getFailureCode(),
                    validationResponseCode.getUserNotVerifiedMessage()));
        }
        if(!usersService.login(loginDto))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getFailureCode(),
                    validationResponseCode.getUserLoginFailedMessage()));
        }

        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getUserLoginSuccessMessage()));
    }
    @PutMapping(value = EndpointURI.FORGOT_PASSWORD)
    public ResponseEntity<Object> forgotPassword(@PathVariable String username)
    {
        if(!usersService.existsByNameIgnoreCase(username))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getUserNotExistsCode(),
                    validationResponseCode.getUserNotExistsMessage()));
        }
        if(!usersService.isActive(username))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getFailureCode(),
                    validationResponseCode.getUserNotVerifiedMessage()));
        }
        usersService.generateOtp(username);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getUserRegenerateOtpSuccessMessage()));

    }
    @PutMapping(value = EndpointURI.RECREATE_PASSWORD)
    public ResponseEntity<Object> recreatePassword(@PathVariable String username, @PathVariable String password, @PathVariable String otp) {
        if (!usersService.existsByNameIgnoreCase(username)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getUserNotExistsCode(),
                    validationResponseCode.getUserNotExistsMessage()));
        }

        if (!usersService.isActive(username)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getFailureCode(),
                    validationResponseCode.getUserNotVerifiedMessage()));
        }

        if (!usersService.checkTimeLimit(otp, username)) {
            usersService.generateOtp(username);
            return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                    validationResponseCode.getCommonSuccessCode(),
                    validationResponseCode.getUserPasswordNotChangeSuccessMessage()));
        }
        usersService.recreatePassword(username, password, otp);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getUserPasswordChangeSuccessMessage()));
    }

    @DeleteMapping(value = EndpointURI.DELETE_ACCOUNT)
    public ResponseEntity<Object> deleteAccount(@PathVariable String username)
    {
        if(!usersService.existsByNameIgnoreCase(username))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getUserNotExistsCode(),
                    validationResponseCode.getUserNotExistsMessage()));
        }
        if(!usersService.isActive(username))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.WARNING.getStatus(),
                    validationResponseCode.getFailureCode(),
                    validationResponseCode.getUserNotVerifiedMessage()));
        }
        usersService.deActivateAccount(username);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationResponseCode.getCommonSuccessCode(),
                validationResponseCode.getUserAccountDeactivateSuccessMessage()));
    }
}



