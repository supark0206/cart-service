package com.assignment.cartservice.service;

import com.assignment.cartservice.dto.OrderDetailDto;
import com.assignment.cartservice.dto.response.OrderResponse;
import com.assignment.cartservice.dto.response.OrderTotalResponse;
import com.assignment.cartservice.entity.User.CustomUserDetails;

import java.util.List;

public interface OrderService {

    int orderFromCart(int userInfoId,  List<OrderDetailDto> orderDetailDtoList);

    List<OrderTotalResponse> orderResponseList(CustomUserDetails customUserDetails);

}
