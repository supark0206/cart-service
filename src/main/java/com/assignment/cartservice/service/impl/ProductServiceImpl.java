package com.assignment.cartservice.service.impl;


import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.ProductSearchDto;
import com.assignment.cartservice.dto.response.ProductPage;
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
    public ProductPage productSearchPage(ProductSearchDto productSearchDto) {

        int totalCount = productMapper.searchCountProduct(productSearchDto);
        int pageNum = productSearchDto.getPageNum();
        int pageSize = productSearchDto.getPageSize();

        int offset = (pageNum - 1) * pageSize;

        int totalPages = totalCount / pageSize;

        if (totalCount % pageSize > 0) {
            totalPages++;
        }

        return ProductPage.builder()
                .pageNum(pageNum)
                .totalPage(totalPages)
                .productResponseList(productMapper.searchProducts(productSearchDto, offset, pageSize))
                .build();
    }
}
