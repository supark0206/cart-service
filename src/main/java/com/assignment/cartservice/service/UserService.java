package com.assignment.cartservice.service;

import com.assignment.cartservice.config.jwt.TokenDto;
import com.assignment.cartservice.dto.UserInfo;
import com.assignment.cartservice.entity.User.CustomUserDetails;

public interface UserService {
    public int join(UserInfo userInfo);

    public TokenDto login(String email, String password);
}
