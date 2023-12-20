package com.assignment.cartservice.service;

import com.assignment.cartservice.config.annotation.LoginUser;
import com.assignment.cartservice.dto.CartDto;
import com.assignment.cartservice.dto.response.CartResponse;
import com.assignment.cartservice.dto.response.OrderResponse;
import com.assignment.cartservice.entity.User.CustomUserDetails;


import java.util.List;

public interface CartService {

    //장바구니 등록
    int saveOrUpdate(CartDto cartDto);

    //장바구니 리스트
    List<CartResponse> cartResponseList(String email);

    //장바구니 선택 삭제
    int delete(int userInfoId, List<Integer> cartIdList);

    //장바구니 전체 삭제
    int deleteAll(int userInfoId);
}
