package com.login.Login.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:MessagesAndCodes.properties")
@Getter
@Setter
public class ValidationResponseCode {
    @Value("${code.success.common}")
    private String commonSuccessCode;

    @Value("${code.failure.common}")
    private String failureCode;

    // Validation code for User
    @Value("${code.validation.user.alreadyExists}")
    private String userAlreadyExists;


    @Value("${code.validation.user.notExists}")
    private String userNotExistsCode;

    // Messages for User
    @Value("${message.success.save.user}")
    private String saveUserSuccessMessage;
    @Value("${message.success.user.verified}")
    private String verifiedAccountMessage;
    @Value("${message.validation.verify.user}")
    private String userVerificationFailedMessage;

    @Value("${message.validation.user.alreadyExists}")
    private String validationUserAlreadyExists;

    @Value("${message.success.getAll.user}")
    private String getAllUserSuccessMessage;

    @Value("${message.validation.user.notExists}")
    private String userNotExistsMessage;
    @Value("${message.validation.user.notVerified}")
    private String userNotVerifiedMessage;
    @Value("${message.validation.user.verified}")
    private String userVerifiedMessage;
    @Value("${message.validation.user.loginFailed}")
    private String userLoginFailedMessage;
    @Value("${message.success.user.loginSuccess}")
    private String userLoginSuccessMessage;
    @Value("${message.success.getById.user}")
    private String getUserByIdSuccessMessage;
    @Value("${message.success.user.regenerate.otp.send}")
    private String userRegenerateOtpSuccessMessage;
    @Value("${message.success.user.change.password}")
    private String userPasswordChangeSuccessMessage;
    @Value("${message.success.user.not.change.password}")
    private String userPasswordNotChangeSuccessMessage;
    @Value("${message.validation.time.limit.exceed}")
    private String timeLimitExceedMessage;
    @Value("${message.success.user.deactivate}")
    private String userAccountDeactivateSuccessMessage;
    @Value("${message.success.update.user}")
    private String updateUserSuccessMessage;

    @Value("${message.success.delete.user}")
    private String deleteUserSuccessMessage;

    @Value("${message.success.searchAndPagination.user}")
    private String searchAndPaginationUserSuccessMessage;



}
