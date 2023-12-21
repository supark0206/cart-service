### 애플리케이션의 실행 방법 (엔드포인트 호출 방법 포함)

---
1. http://3.35.92.167:8080 으로 접근
2. **login, join 을 제외한 api 는 jwt 토큰이 필요합니다.**
3. 테스트 계정  
   - 아이디 : test@naver.com
   - 비밀번호 : test12345678@
4. 포스트맨 문서 URL : https://documenter.getpostman.com/view/17408188/2s9Ykq6fZs#cd9f2b6f-cee4-4f1b-8e5e-51c4a64ac4b8
<br><br>

### CI/CD

---
![image](https://github.com/supark0206/cart-service/assets/71696834/453a9115-a727-41c3-b6b9-292bc2bcb04e)



### 데이터베이스 테이블 구조

---
![image](https://github.com/supark0206/cart-service/assets/71696834/f23554d5-094f-42f6-b23c-5fdc5e4d5286)


### 구현 방법 및 이유에 대한 간략한 설명 / API문서

---
- **모델링**
  - userInfo(사용자), product(상품), orders(주문), order_detail(주문상세내역), cart(장바구니) 테이블을 만들었습니다.

 <br/> <br/> <br/>

- **회원가입을 진행합니다. [POST]http://3.35.92.167:8080/api/join**
이메일 (@를 포함한 이메일 형식에 맞춰야합니다.)
비밀번호 (비밀번호는 8~15 길이에 영문, 숫자, 특수문자 조합으로 이루어져야 합니다.)

    
- request
```json
{   
  "email":"test@naver.com",
  "password":"test12345678@",
  "name":"이름"
}
  ```
  
- response
  
```json
// 이메일 형식에 맞지 않는 경우 
{
  "status": 409,
  "error": "CONFLICT",
  "code": "EXIST_USER_EMAIL",
  "message": "중복된 이메일이 존재합니다."
}
```

```json
// 비밀번호 형식에 맞지 않는 경우
{
  "status": 400,
  "error": "userInfo",
  "code": "BAD_REQUEST_JOIN_PATTERN",
  "message": "비밀번호는  8~15 길이에 영문, 숫자, 특수문자 조합으로 이루어져야 합니다."
}
```

```json
// 중복된 이메일이 있는 경우
{
  "status": 409,
  "error": "CONFLICT",
  "code": "EXIST_USER_EMAIL",
  "message": "중복된 이메일이 존재합니다."
}
```

```json
// 회원가입 성공
{
  "id": 1,
  "message": "회원가입에 성공하였습니다."
}
```
 <br/> <br/> <br/>

- **로그인 [POST]http://3.35.92.167:8080/api/login**

- request
```json
{
  "email":"test@naver.com",
  "password":"test12345678@"
}
```

- response

```json
//로그인 성공
{
  "grantType": "xxx",
  "accessToken": "xxx",
  "refreshToken": "xxx"
}
```
 <br/> <br/> <br/>

- **쇼핑목록 추가(상품 등록) [POST]http://3.35.92.167:8080/api/product**

- request
```json
{   
"name":"상품명",
"price":10000,
"content":"FREE",
"stock":10
 }
```

- response
```json
{
  "id": 1,
  "message": "상품 등록에 성공하였습니다."
}
```
 
<br/> <br/> <br/>


- **쇼핑목록 조회(상품 조회) [GET]http://3.35.92.167:8080/api/product/{pageNum}/{pageSize}/{type}/{keyword}**
  - 페이징 처리를 하여 조회 할 수 있도록 개발하였습니다.
  - pageNum : 조회할 페이지
  - pageSize : 페이지별 조회할 상품 수

- response
```json
{
    "pageNum": 1,
    "totalPage": 3,
    "productResponseList": [
        {
            "id": 1,
            "userName": "26",
            "name": "상품명",
            "price": 10000,
            "content": "FREE",
            "stock": 10,
            "uploadDate": "2023-12-19T06:38:07"
        },
        {
            "id": 2,
            "userName": "26",
            "name": "상품명1",
            "price": 10000,
            "content": "FREE",
            "stock": 2,
            "uploadDate": "2023-12-19T15:44:05"
        },
        {
            "id": 3,
            "userName": "26",
            "name": "상품명2",
            "price": 10000,
            "content": "FREE",
            "stock": 10,
            "uploadDate": "2023-12-19T15:44:08"
        }
    ]
}
 ```

- **장바구니 등록 [POST]http://3.35.92.167:8080/api/cart**
  - productId 상품 아이디
  - stock : 카트에 담을 상품 수

- request
```json
{
  "productId":4,
  "stock":10
}
```

- response
```json
{
  "id": 1,
  "message": "장바구니 등록에 성공하였습니다."
}

```

```json
{
  //존재 하지 않는 상품을 담을 때
  "status": 404,
  "error": "NOT_FOUND",
  "code": "PRODUCT_NOT_FOUND",
  "message": "존재하지 않는 상품입니다."
}
```

<br/> <br/> <br/>

- **장바구니 삭제 [DELETE]http://3.35.92.167:8080/api/cart**
  - 삭제하고 싶은 장바구니 id를 선택해 삭제합니다.
  - 여러개를 선택해서 삭제가 가능합니다.

- request
```json
[1, 3, 5] 
```

- response
```json
{
  "id": 1,
  "message": "장바구니 상품 삭제에 성공하였습니다."
}

```

```json
{
  "status": 400,
  "error": "BAD_REQUEST",
  "code": "CART_DELETE_ERROR",
  "message": "장바구니 상품 삭제에 실패하였습니다."
}
```

<br/> <br/> <br/>

- **장바구니 전체 삭제 [DELETE]http://3.35.92.167:8080/api/cart/all**
  - 내가 담은 모든 장바구니 상품을 삭제합니다

- request
```json
[1, 3, 5] 
```

- response
```json
{
  "id": 2,
  "message": "장바구니에서 2개의 상품 삭제에 성공하였습니다."
}

```

```json
{
  //이미 비어있거나 상품 삭제에 실패시
  "status": 400,
  "error": "BAD_REQUEST",
  "code": "CART_DELETE_ERROR",
  "message": "장바구니 상품 삭제에 실패하였습니다."
}
```

<br/> <br/> <br/>

- **장바구니 조회 [GET]http://3.35.92.167:8080/api/cart**
  - 내가 담은 모든 장바구니 상품을 삭제합니다

- response
```json
[
  {
    "id": 18,
    "productId": 4,
    "name": "상품명3",
    "price": 10000,
    "stock": 10,
    "totalPrice": 100000
  },
  {
    "id": 19,
    "productId": 5,
    "name": "상품명3",
    "price": 10000,
    "stock": 10,
    "totalPrice": 100000
  },
  {
    "id": 20,
    "productId": 8,
    "name": "낚시대",
    "price": 10000,
    "stock": 5,
    "totalPrice": 50000
  }
]
```

<br/> <br/> <br/>


- **상품 주문 [POST]http://3.35.92.167:8080/api/order**
  - 장바구니에 담은 상품을 구매합니다.
  - cartId 장바구니 번호와 quantity 수량을 받습니다.
  - 주문하면 장바구니에서 사라지고 주문한 수량만큼 상품의 재고가 감소합니다.
  - 수량을 받는 이유는 장바구니 목록이 뿌려져있어도 수량은 프론트에서 언제든 변할수있는 숫자이기 때문입니다.

- request
```json
[
  {
    "cartId": 22,
    "quantity": 3
  } ,
  {
    "cartId": 23,
    "quantity": 1
  } ,
  {
    "cartId": 24,
    "quantity": 5
  }
]

```


- response

```json
{
  "id": 3,
  "message": "3 개의 상품 주문에 성공하였습니다."
}
```


```json
{
  "status": 400,
  "error": "BAD_REQUEST",
  "code": "PRODUCT_OUT_OF_STOCK_ERROR",
  "message": "상품 재고가 부족합니다."
}
```

```json
{
  "status": 400,
  "error": "BAD_REQUEST",
  "code": "CART_EXIST_ERROR",
  "message": "장바구니에 상품이 존재하지 않습니다."
}
```

<br/> <br/> <br/>



- **주문 조회 [GET]http://3.35.92.167:8080/api/order**

- response
```json
[
  {
    "orderResponse": {
      "ordersId": 66,
      "totalOrderCnt": 3,
      "totalPriceSum": 90000,
      "orderDate": "2023-12-20T17:38:55"
    },
    "orderDetailResponseList": [
      {
        "productId": 2,
        "productName": "상품명1",
        "quantity": 3,
        "orderPrice": 30000
      },
      {
        "productId": 4,
        "productName": "상품명3",
        "quantity": 1,
        "orderPrice": 10000
      },
      {
        "productId": 5,
        "productName": "상품명3",
        "quantity": 5,
        "orderPrice": 50000
      }
    ]
  },
  {
    "orderResponse": {
      "ordersId": 67,
      "totalOrderCnt": 1,
      "totalPriceSum": 30000,
      "orderDate": "2023-12-20T17:42:00"
    },
    "orderDetailResponseList": [
      {
        "productId": 7,
        "productName": "낚시대",
        "quantity": 3,
        "orderPrice": 30000
      }
    ]
  }
]
```


<br/> <br/> <br/>
