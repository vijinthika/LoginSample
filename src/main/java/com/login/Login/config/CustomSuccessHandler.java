//package com.login.Login.config;
//
//import com.login.Login.Repository.UsersRepository;
//import com.login.Login.request.dto.RegisterDto;
//import com.login.Login.service.UsersService;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CustomSuccessHandler implements AuthenticationSuccessHandler {
//    @Autowired
//    private UsersService usersService;
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException
//    {
//
//        String redirectUrl = null;
//        if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
//            DefaultOAuth2User  userDetails = (DefaultOAuth2User ) authentication.getPrincipal();
//            String username = userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login")+"@gmail.com" ;
//            if(usersRepository.findByEmail(username) == null) {
//                RegisterDto user = new RegisterDto();
//                user.setEmail(username);
//                user.setName(userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login"));
//                user.setPassword(("Dummy"));
//                user.setRole("USER");
//                usersService.saveUser(user);
//            }
//        }  redirectUrl = "/dashboard";
//        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
//    }
//
////    public  void onAu
//
//}
