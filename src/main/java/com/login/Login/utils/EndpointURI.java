package com.login.Login.utils;

public class EndpointURI {
    private static final String BASE_API_PATH = "/api/v1/";
    private static final String EMAIL = "/{email}";
    private static final String USERNAME = "/{username}";
    private static final String PASSWORD = "/{password}";
    private static final String OTP = "/{otp}";
    public static final String USER_REGISTER = BASE_API_PATH + "register";
    public static final String USER_VERIFICATION = USER_REGISTER + USERNAME + OTP;
    public static final String USER_LOGIN = BASE_API_PATH + "login";
    public static final String USER_ACCOUNT = BASE_API_PATH + "userAccount";
    public static final String FORGOT_PASSWORD = BASE_API_PATH + "forgot_password"+USERNAME;
    public static final String RECREATE_PASSWORD = FORGOT_PASSWORD +PASSWORD+OTP ;
    public static final String DELETE_ACCOUNT = BASE_API_PATH + "de_activate_account"+USERNAME;
}
