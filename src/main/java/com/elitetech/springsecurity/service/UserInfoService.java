package com.elitetech.springsecurity.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.dto.UserDTO;
import com.elitetech.springsecurity.entity.UserInfo;
import com.elitetech.springsecurity.mapper.UserInfoMapper;

import com.elitetech.springsecurity.repository.UserInfoRepository;


import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
@Service

public class UserInfoService implements UserDetailsService {
    @Autowired
    private  UserInfoRepository userInfoRepository;
 
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public UserInfo getUserByEmail(String email) {
        return userInfoRepository.findByEmail(email).orElse(null);
    }

    public UserInfo getOneUser(String name) {
        return userInfoRepository.findByName(name).orElse(null);
    }
    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

       

        return userInfoRepository.save(userInfo);
    }



    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }



    public UserInfo getUser(long id) {
        return userInfoRepository.findById(id).get();
    }
}

