package com.assignment.cartservice.service.impl;

import com.assignment.cartservice.dto.OrderDetailDto;
import com.assignment.cartservice.dto.OrderDto;
import com.assignment.cartservice.dto.response.CartResponse;
import com.assignment.cartservice.dto.response.OrderDetailResponse;
import com.assignment.cartservice.dto.response.OrderResponse;
import com.assignment.cartservice.dto.response.OrderTotalResponse;
import com.assignment.cartservice.entity.User.CustomUserDetails;
import com.assignment.cartservice.exception.CustomException;
import com.assignment.cartservice.exception.ErrorCode;
import com.assignment.cartservice.mapper.CartMapper;
import com.assignment.cartservice.mapper.OrderMapper;
import com.assignment.cartservice.mapper.ProductMapper;
import com.assignment.cartservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

    private final ProductMapper productMapper;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public int orderFromCart(int userInfoId,  List<OrderDetailDto> orderDetailDtoList) {

        OrderDto orderDto = OrderDto.builder()
                .userInfoId(userInfoId)
                .build();

        orderMapper.saveOrder(orderDto);

        orderDetailDtoList.forEach(
                orderDetailDto -> {
                    
                    int productId = getCartResponse(orderDetailDto, userInfoId).getProductId();
                    int productStock = getCartResponse(orderDetailDto, userInfoId).getStock();
                    int productPrice = productMapper.selectProductPrice(productId);
                    int quantity = orderDetailDto.getQuantity();
                    int orderPrice = productPrice * quantity;


                    productStockUpdate(productId, productStock, quantity);

                    orderDetailDto.setProductId(productId);
                    orderDetailDto.setOrdersId(orderDto.getOrdersId());
                    orderDetailDto.setOrderPrice(orderPrice);
                }
        );

        //주문
        int result = saveOrderDetails(orderDetailDtoList);

        //장바구니 삭제
        deleteCartAfterOrder(userInfoId, orderDetailDtoList);

        return result;

    }




    @Override
    public List<OrderTotalResponse> orderResponseList(CustomUserDetails customUserDetails) {
        int userInfoId = customUserDetails.getUserInfo().getId();

        List<OrderResponse> orderResponseList = orderMapper.selectOrderResponse(userInfoId);
        List<OrderTotalResponse> orderTotalResponses = new ArrayList<>();

        //OrderResponse + orderDetailResponses 를 orderTotalResponses에 담아서 반환
        for (OrderResponse orderResponse : orderResponseList) {
            List<OrderDetailResponse> orderDetailResponses = orderMapper.selectOrderDetailResponse(userInfoId, orderResponse.getOrdersId());
            OrderTotalResponse orderTotalResponse = OrderTotalResponse.builder()
                    .orderResponse(orderResponse)
                    .orderDetailResponseList(orderDetailResponses)
                    .build();
            orderTotalResponses.add(orderTotalResponse);
        }

        return orderTotalResponses;
    }

    //cart id 로 상품 조회 , 장바구니에 존재하는 상품인지 체크
    private CartResponse getCartResponse(OrderDetailDto orderDetailDto, int userInfoId) {

        return cartMapper.findByCartId(orderDetailDto.getCartId(), userInfoId).orElseThrow(
                () ->  new CustomException(ErrorCode.CART_EXIST_ERROR)
        );

    }

    //주문시 재고 변경
    private void productStockUpdate(int productId, int productStock, int quantity) {
        if(productStock < quantity){
            throw new CustomException(ErrorCode.PRODUCT_OUT_OF_STOCK_ERROR);
        }
        productMapper.updateStock(productId, quantity);
    }

    private void deleteCartAfterOrder(int userInfoId, List<OrderDetailDto> orderDetailDtoList) {
        List<Integer> cartIdList = orderDetailDtoList.stream()
                .map(OrderDetailDto::getCartId)
                .collect(Collectors.toList());

        int result = cartMapper.delete(userInfoId,cartIdList);

        if (result == 0) {
            throw new CustomException(ErrorCode.CART_DELETE_ERROR);
        }
    }

    private int saveOrderDetails(List<OrderDetailDto> orderDetailDtoList) {
        int result = orderMapper.saveDetails(orderDetailDtoList);

        if (result == 0) {
            throw new CustomException(ErrorCode.ORDER_ERROR);
        }
        return result;
    }
}
