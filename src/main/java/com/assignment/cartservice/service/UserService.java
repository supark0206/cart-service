package com.assignment.cartservice.service;

import com.assignment.cartservice.dto.TokenDto;
import com.assignment.cartservice.dto.UserInfo;

public interface UserService {
    public int join(UserInfo userInfo);

    public TokenDto login(String email, String password);
}
