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

    @Value("${message.validation.verify.user}")
    private String userVerificationFailedMessage;

    @Value("${message.validation.user.alreadyExists}")
    private String validationUserAlreadyExists;

    @Value("${message.success.getAll.user}")
    private String getAllUserSuccessMessage;

    @Value("${message.validation.user.notExists}")
    private String userNotExistsMessage;

    @Value("${message.validation.user.verified}")
    private String userVerifiedMessage;
    @Value("${message.success.getById.user}")
    private String getUserByIdSuccessMessage;

    @Value("${message.success.update.user}")
    private String updateUserSuccessMessage;

    @Value("${message.success.delete.user}")
    private String deleteUserSuccessMessage;

    @Value("${message.success.searchAndPagination.user}")
    private String searchAndPaginationUserSuccessMessage;



}
