package com.project.studentmgtsystemproject.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserDetailsImpl implements UserDetails{

    private Long Id;

    private String username;

    private String name;

    private Boolean isAdvisor;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority>authorities;


    public UserDetailsImpl(Long Id, String username, String name, Boolean isAdvisor, String password, String role){
        this.Id = Id;
        this.username = username;
        this.name = name;
        this.isAdvisor = isAdvisor;
        this.password = password;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        this.authorities = grantedAuthorities;
        // this a constructor
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        // matching the class types
        if(this == null || getClass() != o.getClass()){
            return false;
        }
        //matching the id.s
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(Id, user.getId());
    }
}
