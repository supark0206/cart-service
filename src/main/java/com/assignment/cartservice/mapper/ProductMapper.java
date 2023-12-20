package com.assignment.cartservice.mapper;

import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.ProductSearchDto;
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

    //아이디로 상품 조회
    Optional<ProductResponse> findById(@Param("id") int id);

    //검색 상품 카운트
    int searchCountProduct(@Param("productSearchDto") ProductSearchDto productSearchDto);

    //상품 검색
    List<ProductResponse> searchProducts(@Param("productSearchDto") ProductSearchDto productSearchDto,
                                 @Param("offset") int offset, @Param("limit") int limit);

    //상품 가격 조회
    int selectProductPrice(@Param("productId") int productId);

}
