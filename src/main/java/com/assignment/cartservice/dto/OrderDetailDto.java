package com.assignment.cartservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailDto {
    
    private int id;

    /** 장바구니 번호 */
    private int cartId;
    
    /** 주문 번호 */
    private int ordersId;

    /** 주문한 상품 번호 */
    private int productId;
    
    /** 상품 주문 수량 */
    private int quantity;

    /** 상품별 주문 금액 ( 상품 가격 * 주문 수량 )*/
    private int orderPrice;
}
