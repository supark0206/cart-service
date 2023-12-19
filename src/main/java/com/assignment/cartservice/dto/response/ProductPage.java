package com.assignment.cartservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductPage {

    /** 현재 페이지 */
    private int pageNum;

    /** 총 페이지 수 */
    private int totalPage;

    private List<ProductResponse> productResponseList;

}
