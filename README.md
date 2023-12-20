### 애플리케이션의 실행 방법 (엔드포인트 호출 방법 포함)

---
1. 해당 repository clone
2. RecruitApplication 실행
3. http://3.35.92.167:8080 으로 접근

<br><br>

### 데이터베이스 테이블 구조

---
![image](https://github.com/supark0206/cart-service/assets/71696834/f23554d5-094f-42f6-b23c-5fdc5e4d5286)


### 구현 방법 및 이유에 대한 간략한 설명 / API문서

---
- **모델링**
  - userInfo(사용자), product(상품), orders(주문), order_detail(주문상세내역), cart(장바구니) 테이블을 만들었습니다.


- **회원가입을 진행합니다. [POST]http://3.35.92.167:8080/api/join**
회원가입
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
  // 이메일 형식에 맞지 않는 경우
  ```json 
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
