package com.assignment.cartservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {

    private int id;

    //상품번호
    private int productId;

    //상품명
    private String name;

    //가격
    private int price;

    //상품 재고
    private int stock;

    //전체가격
    private int totalPrice;

}
