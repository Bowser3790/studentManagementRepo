package com.project.studentmgtsystemproject.security.config;

import com.project.studentmgtsystemproject.security.jwt.AuthEntryPointJwt;
import com.project.studentmgtsystemproject.security.jwt.AuthTokenFilter;
import com.project.studentmgtsystemproject.security.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    /**
     * this class is the main configuration class of SECURITY
     * ALl implementation will be injected in this class and be used a configuration.
     */

    private final UserDetailServiceImpl userDetailService;

    private final AuthEntryPointJwt unauthorizedHandler;

    /**
     *
     * This will be our authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    /**
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                // we configured unauthorized exception handler
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // we configured session management
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // we added white list
                .authorizeRequests().antMatchers(AUTH_WHITE_LIST).permitAll()
                // except whiteList we authenticate all requests
                .anyRequest().authenticated();

        http.headers().frameOptions().sameOrigin();
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * This will be our token Filter
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();

    }

    /**
     * This will be our authenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    /**
     * This will be our password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * This will be our CORS configuration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);
                // we allow all URL's
                registry.addMapping("/**")
                        // we allow all origins
                        .allowedOrigins("*")
                        // we allow all headers
                        .allowedHeaders("*")
                        // We allow all HTTP methods
                        .allowedMethods("*");

            }
        };
    }
    private static final String AUTH_WHITE_LIST[] = {
            "/",
            "/index*",
            "/static/**",
            "/*.js",
            "/*.json",
            "/contactMessage/save",
            "/auth/login"
    };


}
