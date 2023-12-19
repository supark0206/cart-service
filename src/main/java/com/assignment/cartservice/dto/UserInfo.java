package com.assignment.cartservice.dto;

import com.assignment.cartservice.entity.User.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private int id;

    /** 이메일 */
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다")
    private String email;

    /** 비밀번호 */
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
            message = "비밀번호는  8~15 길이에 영문, 숫자, 특수문자 조합으로 이루어져야 합니다.")
    private String password;

    /** 이름 */
    private String name;

    /** 권한 */
    private UserRole role;
}
