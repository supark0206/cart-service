package com.assignment.cartservice.mapper;

import com.assignment.cartservice.dto.UserInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper{
    Optional<UserInfo> findByEmail(@Param("email") String email);

    int countEmail(@Param("email") String email);

    int save(@Param("userInfo") UserInfo userInfo);

}
