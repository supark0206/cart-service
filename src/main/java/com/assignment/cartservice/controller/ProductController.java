package com.assignment.cartservice.controller;

import com.assignment.cartservice.config.annotation.LoginUser;
import com.assignment.cartservice.dto.*;
import com.assignment.cartservice.dto.response.ProductResponse;
import com.assignment.cartservice.dto.response.ResultResponse;
import com.assignment.cartservice.entity.User.CustomUserDetails;
import com.assignment.cartservice.service.ProductService;
import com.assignment.cartservice.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

/*    @PostMapping("")
    public List<ProductDto> productDtoList() {

        return me;
    }*/

    @PostMapping("")
    public ResponseEntity<ResultResponse> save(@LoginUser CustomUserDetails customUserDetails, @RequestBody ProductDto productDto) {

        productDto.setUserInfoId(customUserDetails.getUserInfo().getId());

        int id = productService.save(productDto);
        String message = "상품 등록에 성공하였습니다.";

        return ResponseEntity.ok(new ResultResponse(id,message));
    }

    @GetMapping("")
    public List<ProductResponse> productDtoList() {
        return productService.productAll();
    }

}
