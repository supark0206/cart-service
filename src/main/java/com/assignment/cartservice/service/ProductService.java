package com.assignment.cartservice.service;

import com.assignment.cartservice.dto.ProductDto;
import com.assignment.cartservice.dto.SearchPaginationDto;
import com.assignment.cartservice.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    int save(ProductDto productDto);

    List<ProductResponse> productAll();
}
