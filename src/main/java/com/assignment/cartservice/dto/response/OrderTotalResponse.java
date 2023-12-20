package com.assignment.cartservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderTotalResponse {

    private OrderResponse orderResponse;

    private List<OrderDetailResponse> orderDetailResponseList;

}
