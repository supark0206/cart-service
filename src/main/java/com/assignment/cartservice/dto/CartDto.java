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

    /** user FK */
    private int userInfoId;

    /** product FK */
    private int productId;

    /** 장바구니 수량 */
    private int stock;
}
