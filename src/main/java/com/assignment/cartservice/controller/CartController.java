package com.assignment.cartservice.controller;

import com.assignment.cartservice.config.annotation.LoginUser;
import com.assignment.cartservice.dto.CartDto;
import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.response.CartResponse;
import com.assignment.cartservice.dto.response.ResultResponse;
import com.assignment.cartservice.entity.User.CustomUserDetails;
import com.assignment.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> save(@LoginUser CustomUserDetails customUserDetails, @RequestBody CartDto cartDto) {

        cartDto.setUserInfoId(customUserDetails.getUserInfo().getId());

        int id = cartService.save(cartDto);
        String message = "장바구니 등록에 성공하였습니다.";

        return ResponseEntity.ok(new ResultResponse(id,message));
    }


    @GetMapping("")
    public List<CartResponse> cartResponseList(@LoginUser CustomUserDetails customUserDetails) {

        return cartService.cartResponseList(customUserDetails.getUsername());

    }

    @DeleteMapping("")
    public String save(){
        return "test";
    }
}
