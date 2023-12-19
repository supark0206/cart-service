package com.assignment.cartservice.dto;

import lombok.Data;

@Data
public class SearchPaginationDto {

    //페이지 번호
    private int pageNo;

    //한 페이지당 데이터 수
    private int amount;

    //검색에 필요한 데이터를 변수로 선언.
    private String keyword;
    private String condition;

}
