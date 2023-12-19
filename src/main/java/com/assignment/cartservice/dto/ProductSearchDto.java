package com.assignment.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchDto {

    /** 현재 페이지 */
    private int pageNum;

    /** 페이지별 목록 갯수 */
    private int pageSize;

    /** 검색 타입 */
    private String type;

    /** 검색 문장 */
    private String keyword;
}
