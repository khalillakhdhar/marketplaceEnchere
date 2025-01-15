package com.elitetech.springsecurity.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.elitetech.springsecurity.entity.UserInfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(UserInfo userInfo) {
        this.userName = userInfo.getEmail();
        this.password = userInfo.getPassword();

        // Safely parse roles from comma-separated string
        this.authorities = (userInfo.getRoles() != null && !userInfo.getRoles().isEmpty())
                ? Arrays.stream(userInfo.getRoles().split(","))
                        .map(String::trim) // Remove any extra spaces
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
                : List.of(); // Default to empty list if roles are null or empty
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
        return userName;
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
}
