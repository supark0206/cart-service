package com.assignment.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private int id;

    //user id
    private int userInfoId;

    //product id
    private int productId;

    //장바구니에 담은 수량
    private int stock;
}
