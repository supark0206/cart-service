package com.assignment.cartservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;

    /** userInfo Fk */
    @NotBlank(message = "로그인 사용자만 상품을 등록 할 수 있습니다.")
    private int userInfoId;

    /** 이름 */
    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String name;

    /** 가격 */
    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private int price;

    /** 상품 설명 */
    @NotBlank(message = "상세설명은 필수 입력 값입니다.")
    private String content;

    /** 상품 재고 */
    @NotBlank(message = "상품 재고는 필수 입력 값입니다.")
    private int stock;

    /** 상품 등록 시간 */
    private LocalDateTime uploadDate;
}
