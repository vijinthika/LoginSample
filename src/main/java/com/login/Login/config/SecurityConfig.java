//package com.login.Login.config;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableAutoConfiguration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
////    @Autowired
////    private UsersService usersService;
////    @Autowired
////    private AuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login()
//        ;
//        return http.build();
//    }
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
////        auth.setUserDetailsService(usersService);
////        auth.setPasswordEncoder(bCryptPasswordEncoder());
////        return auth;
////    }
//
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
////        return authenticationConfiguration.getAuthenticationManager();
////    }
//
//    //    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/registration/**", "/login/**").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin().loginPage("/login").successHandler(authenticationSuccessHandler)
////                .and().csrf().disable()
////                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
////                .and().oauth2Login().loginPage("/login").successHandler(authenticationSuccessHandler);
////        return http.build();
////
////    }
//
//}
