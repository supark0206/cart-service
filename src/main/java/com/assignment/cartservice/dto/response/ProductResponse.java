package com.assignment.cartservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponse {
    //등록자 이름
    private String userName;

    //이름
    private String name;

    //가격
    private int price;

    //상품 설명
    private String content;

    //상품 재고
    private int stock;

    //상품 등록 시간
    private LocalDateTime uploadDate;
}
