package com.assignment.cartservice.service;

import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.ProductSearchDto;
import com.assignment.cartservice.dto.response.ProductPage;

public interface ProductService {

    int save(ProductDto productDto);

    ProductPage productSearchPage(ProductSearchDto productSearchDto);
}
