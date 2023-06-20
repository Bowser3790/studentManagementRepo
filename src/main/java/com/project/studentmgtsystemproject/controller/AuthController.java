package com.project.studentmgtsystemproject.controller;

import com.project.studentmgtsystemproject.enums.RoleType;
import com.project.studentmgtsystemproject.payload.request.LoginRequest;
import com.project.studentmgtsystemproject.payload.response.AuthResponse;
import com.project.studentmgtsystemproject.security.jwt.JwtUtils;
import com.project.studentmgtsystemproject.security.service.UserDetailServiceImpl;
import com.project.studentmgtsystemproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    public final JwtUtils jwtUtils;

    public final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthResponse>authenticateUser(@RequestBody @Valid LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // we authenticate the username and password
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        // if authenticated successfully, we upload the authentication to the SECURITY CONTEXT
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // we create a JWT token for further requests
        String token = "Bearer" + jwtUtils.generateJtwToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Optional<String>role = roles.stream().findFirst();

        // another way of using builder
        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();
        authResponse.username(userDetails.getUsername());
        authResponse.token(token);
        authResponse.name(userDetails.getName());

        if(role.isPresent()){
            authResponse.role(role.get());
            if(role.get().equalsIgnoreCase(RoleType.TEACHER.name())){
                authResponse.isAdvisory(String.valueOf(userDetails.getIsAdvisor())).toString();
            }
        }
        return null;

    }

}
