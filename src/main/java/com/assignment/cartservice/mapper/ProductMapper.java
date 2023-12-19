package com.assignment.cartservice.mapper;

import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.UserInfo;
import com.assignment.cartservice.dto.response.ProductResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    //상품 저장
    int save(@Param("productDto") ProductDto productDto);

    //전체 상품 조회
    List<ProductResponse> productAll();

    //아이디로 상품 조회
    Optional<ProductResponse> findById(@Param("id") int id);

}
