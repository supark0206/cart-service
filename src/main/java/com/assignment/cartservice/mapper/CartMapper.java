package com.assignment.cartservice.mapper;

import com.assignment.cartservice.dto.CartDto;
import com.assignment.cartservice.dto.response.CartResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    int save(@Param("cartDto") CartDto cartDto);

    List<CartResponse> cartAll(@Param("email") String email);

}
