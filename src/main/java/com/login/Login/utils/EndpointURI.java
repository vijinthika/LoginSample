package com.login.Login.utils;

public class EndpointURI {
    private static final String BASE_API_PATH = "/api/v1/";
    private static final String EMAIL = "/{email}";
    private static final String OTP = "/{otp}";
    public static final String USERREGISTER=BASE_API_PATH+"register";
    public static final String USERVERIFICATION=USERREGISTER+EMAIL+OTP;
    public static final String USERLOGIN=BASE_API_PATH+"login";
    public static final String USERACCOUNT=BASE_API_PATH+"userAccount";
}
