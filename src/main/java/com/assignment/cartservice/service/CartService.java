package com.assignment.cartservice.service;

import com.assignment.cartservice.dto.CartDto;
import com.assignment.cartservice.dto.response.CartResponse;

import java.util.List;

public interface CartService {

    int save(CartDto cartDto);

    List<CartResponse> cartResponseList(String email);

}
