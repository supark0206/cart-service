package com.assignment.cartservice.dto;

import com.assignment.cartservice.entity.User.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;

    //이메일
    private String email;

    //비밀번호
    private String password;

    //이름
    private String name;

    //권한
    private UserRole role;
}
