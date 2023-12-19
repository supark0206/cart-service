package com.assignment.cartservice.controller;

import com.assignment.cartservice.dto.TokenDto;
import com.assignment.cartservice.dto.UserInfo;
import com.assignment.cartservice.dto.UserLogin;
import com.assignment.cartservice.dto.response.ResultResponse;
import com.assignment.cartservice.exception.CustomException;
import com.assignment.cartservice.exception.ErrorCode;
import com.assignment.cartservice.mapper.UserMapper;
import com.assignment.cartservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/join")
    public ResponseEntity<ResultResponse> join(@Valid @RequestBody UserInfo userInfo) {

        int id = userService.join(userInfo);
        String message = "회원가입에 성공하였습니다.";

        return ResponseEntity.ok(new ResultResponse(id,message));
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserLogin userLogin) {

        String memberId = userLogin.getEmail();
        String password = userLogin.getPassword();

        return userService.login(memberId, password);
    }

    @GetMapping("/user/find")
    public String email(@RequestParam String email) {

        System.out.println("email = " + email);

        UserInfo userInfo = userMapper.findByEmail(email).orElseThrow(
                ()-> new CustomException(ErrorCode.NOT_FOUND)
        );

        String result = userInfo.getName() + " || " + userInfo.getEmail();

        return result;
    }



}
