package com.assignment.cartservice.mapper;

import com.assignment.cartservice.dto.CartDto;
import com.assignment.cartservice.dto.response.CartResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    //장바구니 등록
    int save(@Param("cartDto") CartDto cartDto);

    //장바구니 수량 수정
    int updateStock(@Param("cartDto") CartDto cartDto);

    //장바구니 상품 조회
    int findByUserInfoAndProduct(@Param("cartDto") CartDto cartDto);

    //장바구니 전체 조회
    List<CartResponse> cartAll(@Param("email") String email);

    //장바구니 선택 삭제
    int delete(@Param("userInfoId") int userInfoId, @Param("cartIdList") List<Integer> cartIdList);

    //장바구니 전체 삭제
    int deleteAll(@Param("userInfoId") int userInfoId);

    //장바구니 상품 아이디 조회
    Optional<CartResponse> findByCartId(@Param("cartId") int cartId, @Param("userInfoId") int userInfoId);


}
