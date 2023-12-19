package com.assignment.cartservice.dto;

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
    private int userInfoId;

    /** 이름 */
    private String name;

    /** 가격 */
    private int price;

    /** 상품 설명 */
    private String content;

    /** 상품 재고 */
    private int stock;

    /** 상품 등록 시간 */
    private LocalDateTime uploadDate;
}
