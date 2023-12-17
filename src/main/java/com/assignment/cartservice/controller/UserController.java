package com.assignment.cartservice.controller;

import com.assignment.cartservice.config.annotation.LoginUser;
import com.assignment.cartservice.config.jwt.TokenDto;
import com.assignment.cartservice.dto.UserInfo;
import com.assignment.cartservice.dto.response.ResultResponse;
import com.assignment.cartservice.exception.CustomException;
import com.assignment.cartservice.exception.ErrorCode;

import com.assignment.cartservice.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ResultResponse> join(@Valid @RequestBody UserInfo userInfo, HttpSession session) {

        int id = userService.join(userInfo);
        String message = "회원가입에 성공하였습니다.";

        return ResponseEntity.ok(new ResultResponse(id,message));
    }


}
