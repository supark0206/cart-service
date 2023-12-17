package com.assignment.cartservice.service.impl;

import com.assignment.cartservice.dto.UserInfo;
import com.assignment.cartservice.entity.User.CustomUserDetails;
import com.assignment.cartservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userMapper.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email)
        );
        return new CustomUserDetails(userInfo);
    }



}
