package com.assignment.cartservice.mapper;

import com.assignment.cartservice.dto.OrderDetailDto;
import com.assignment.cartservice.dto.OrderDto;
import com.assignment.cartservice.dto.response.OrderDetailResponse;
import com.assignment.cartservice.dto.response.OrderResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrderMapper {

    int saveOrder(@Param("orderDto") OrderDto orderDto);

    int saveDetails(@Param("orderDetailDtoList") List<OrderDetailDto> orderDetailDtoList);

    List<OrderResponse> selectOrderResponse(@Param("userInfoId") int userInfoId);

    List<OrderDetailResponse> selectOrderDetailResponse(@Param("userInfoId") int userInfoId, @Param("ordersId") int ordersId);
}
