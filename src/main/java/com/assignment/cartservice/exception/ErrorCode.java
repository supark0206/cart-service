package com.assignment.cartservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //400 BAD_REQUEST : 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    EMAIL_SEND_ERROR(HttpStatus.BAD_REQUEST,"이메일을 전송할 수 없습니다."),
    EMAIL_FORMAT_ERROR(HttpStatus.BAD_REQUEST, "이메일 주소 형식이 유효하지 않습니다."),
    EMAIL_CODE_CHECK_ERROR(HttpStatus.BAD_REQUEST, "인증 코드가 틀렸습니다."),
    USER_REGISTER_ERROR(HttpStatus.BAD_REQUEST, "회원가입에 실패하였습니다."),
    CART_SAVE_ERROR(HttpStatus.BAD_REQUEST, "장바구니 등록에 실패하였습니다."),
    CART_STOCK_ERROR(HttpStatus.BAD_REQUEST, "담을 수 있는 개수를 초과했습니다."),
    CART_DELETE_ERROR(HttpStatus.BAD_REQUEST, "장바구니 상품 삭제에 실패하였습니다."),
    ORDER_ERROR(HttpStatus.BAD_REQUEST, "장바구니 상품 삭제에 실패하였습니다."),


    //404 NOT_FOUND : 리소스를 찾을 수 없음
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),
    NOT_FOUND_USER_ID(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),


    //409 CONFLICT : 리소스 충돌
    EXIST_USER_EMAIL(HttpStatus.CONFLICT, "중복된 이메일이 존재합니다."),

    //405 METHOD_NOT_ALLOWED : 허용 되지않은 Request Method 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드 호출입니다."),

    //500 INTERNAL_SERVER_ERROR : 내부 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"내부 서버 오류입니다."),
    EMAIL_ENCODING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 인코딩 중 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String MESSAGE;
}