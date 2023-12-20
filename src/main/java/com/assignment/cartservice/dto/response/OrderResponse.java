package com.assignment.cartservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {

    /** 주문 번호 */
    private int ordersId;
    
    /** 총 주문 건 */
    private int totalOrderCnt;

    /** 총 주문 금액 합산 */
    private int totalPriceSum;

    /** 주문 시간 */
    private LocalDateTime orderDate;





}
