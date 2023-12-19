package com.assignment.cartservice.service.impl;


import com.assignment.cartservice.config.jwt.JwtProvider;
import com.assignment.cartservice.dto.TokenDto;
import com.assignment.cartservice.dto.UserInfo;
import com.assignment.cartservice.entity.User.UserRole;
import com.assignment.cartservice.exception.CustomException;
import com.assignment.cartservice.exception.ErrorCode;
import com.assignment.cartservice.mapper.UserMapper;
import com.assignment.cartservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    @Transactional
    @Override
    public int join(UserInfo userInfo) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = userInfo.getPassword();
        userInfo.setPassword(bCryptPasswordEncoder.encode(rawPassword));

        //이메일 중복 검사
        validateEmailNotExist(userInfo.getEmail());

        int result = userMapper.save(
                UserInfo.builder()
                        .email(userInfo.getEmail())
                        .password(userInfo.getPassword())
                        .name(userInfo.getName())
                        .role(UserRole.ROLE_USER)
                        .build()
        );

        if (result == 0) {
            throw new CustomException(ErrorCode.USER_REGISTER_ERROR);
        }

        return result;
    }


    @Override
    public TokenDto login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtProvider.generateToken(authentication);

        return tokenDto;
    }

    private void validateEmailNotExist(String email) {
        if(userMapper.countEmail(email)>=1){
            throw new CustomException(ErrorCode.EXIST_USER_EMAIL);
        }
    }

}
