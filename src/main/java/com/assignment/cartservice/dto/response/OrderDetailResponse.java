package com.assignment.cartservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailResponse {

    /** 주문한 상품 번호 */
    private int productId;

    /** 주문한 상품명 */
    private String productName;

    /** 상품 주문 수량 */
    private int quantity;

    /** 상품별 주문 금액 ( 상품 가격 * 주문 수량 )*/
    private int orderPrice;
}
