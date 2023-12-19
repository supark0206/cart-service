package com.assignment.cartservice.service.impl;


import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.SearchPaginationDto;
import com.assignment.cartservice.dto.response.ProductResponse;
import com.assignment.cartservice.mapper.ProductMapper;
import com.assignment.cartservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductServiceImpl implements ProductService {


    private final ProductMapper productMapper;

    @Transactional
    @Override
    public int save(ProductDto productDto) {
        return productMapper.save(productDto);
    }

    @Override
    public List<ProductResponse> productAll() {
        return productMapper.productAll();
    }


}
