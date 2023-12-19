package com.assignment.cartservice.mapper;

import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.response.ProductResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    int save(@Param("productDto") ProductDto productDto);

    List<ProductResponse> productAll();

}
