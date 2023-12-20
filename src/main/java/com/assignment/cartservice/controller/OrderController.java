package com.assignment.cartservice.controller;

import com.assignment.cartservice.config.annotation.LoginUser;
import com.assignment.cartservice.dto.OrderDetailDto;
import com.assignment.cartservice.dto.response.OrderResponse;
import com.assignment.cartservice.dto.response.OrderTotalResponse;
import com.assignment.cartservice.dto.response.ResultResponse;
import com.assignment.cartservice.entity.User.CustomUserDetails;
import com.assignment.cartservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> orderCart(@LoginUser CustomUserDetails customUserDetails, @RequestBody List<OrderDetailDto> orderDetailDtoList) {

        int result = orderService.orderFromCart(customUserDetails.getUserInfo().getId(), orderDetailDtoList);
        String message = result+" 개의 상품 주문에 성공하였습니다.";

        return ResponseEntity.ok(new ResultResponse(result,message));
    }

    @GetMapping("")
    public List<OrderTotalResponse> orderResponseList(@LoginUser CustomUserDetails customUserDetails){
        return orderService.orderResponseList(customUserDetails);
    }

}
