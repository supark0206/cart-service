package com.assignment.cartservice.service.impl;

import com.assignment.cartservice.dto.CartDto;
import com.assignment.cartservice.dto.response.CartResponse;
import com.assignment.cartservice.dto.response.ProductResponse;
import com.assignment.cartservice.exception.CustomException;
import com.assignment.cartservice.exception.ErrorCode;
import com.assignment.cartservice.mapper.CartMapper;
import com.assignment.cartservice.mapper.ProductMapper;
import com.assignment.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CartServiceImpl implements CartService {

    private final ProductMapper productMapper;
    private final CartMapper cartMapper;

    @Transactional
    @Override
    public int save(CartDto cartDto) {

        ProductResponse cartProduct = productMapper.findById(cartDto.getProductId()).orElseThrow(
                () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)
        );

        validateStockError(cartDto, cartProduct);

        int result = cartMapper.save(cartDto);

        if (result == 0) {
            throw new CustomException(ErrorCode.CART_SAVE_ERROR);
        }

        return result;

    }

    @Override
    public List<CartResponse> cartResponseList(String email) {
        return cartMapper.cartAll(email);
    }

    //장바구니에 담는 개수 초과시
    private void validateStockError(CartDto cartDto, ProductResponse cartProduct) {

        if(cartDto.getStock() > cartProduct.getStock()){
            throw new CustomException(ErrorCode.CART_STOCK_ERROR);
        }

    }

}
