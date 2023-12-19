package com.assignment.cartservice.service.impl;

import com.assignment.cartservice.dto.CartDto;
import com.assignment.cartservice.dto.response.CartResponse;
import com.assignment.cartservice.dto.response.ProductResponse;
import com.assignment.cartservice.entity.User.CustomUserDetails;
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
    public int saveOrUpdate(CartDto cartDto) {

        int result;

        productMapper.findById(cartDto.getProductId()).orElseThrow(
                () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)
        );

        if (cartMapper.findByUserInfoAndProduct(cartDto) == 0) {
            result = cartMapper.save(cartDto);
        } else {
            result = cartMapper.update(cartDto);
        }

        if (result == 0) {
            throw new CustomException(ErrorCode.CART_SAVE_ERROR);
        }

        return result;

    }

    @Override
    public List<CartResponse> cartResponseList(String email) {
        return cartMapper.cartAll(email);
    }

    @Transactional
    @Override
    public int delete(int userInfoId, List<Integer> cartIdList) {
        int result = cartMapper.delete(userInfoId, cartIdList);

        if (result == 0) {
            throw new CustomException(ErrorCode.CART_DELETE_ERROR);
        }

        return result;
    }

    @Transactional
    @Override
    public int deleteAll(int userInfoId) {
        int result = cartMapper.deleteAll(userInfoId);

        if (result == 0) {
            throw new CustomException(ErrorCode.CART_DELETE_ERROR);
        }

        return result;
    }


}
