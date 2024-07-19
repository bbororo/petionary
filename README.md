# 🐱  펫과사전 🐶
📅  **프로젝트 기간** : 2024.06 ~ 2024.07
반려동물 용품 쇼핑몰이며 특가상품을 선착순으로 구매할 수 있는 서비스를 제공합니다.

<br>

##  ⚙️ 기술스택 
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/3.3.1-515151?style=for-the-badge">
<img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=Spring Data JPA&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
<br>
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/8.8-515151?style=for-the-badge">
<img src="https://img.shields.io/badge/java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17-515151?style=for-the-badge">
<br>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"><img src="https://img.shields.io/badge/8-515151?style=for-the-badge">
<img src="https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white"><img src="https://img.shields.io/badge/6.2-515151?style=for-the-badge">
<br>
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"><img src="https://img.shields.io/badge/25.0.3-515151?style=for-the-badge">

<br>

##  📈 성능 최적화 및 트러블슈팅



<br>


## 📜 주요 기능
- 기본적인 ecommerce 기능과 Redis를 활용한 동시성 처리를 구현한 프로젝트
  - 대량의 요청이 집중되는 선착순 구매 상황에서 Redis를 활용한 성능 최적화 및 동시성 문제 해결
- 스케줄러를 이용한 주문 상품 상태변경 자동화
<details><summary>회원
</summary>
    - jwt를 이용한 로그인 기능
    - 로그아웃, 유저 정보 변경
</details>

<details><summary>상품
</summary>

1. 상품
    - 전체 상품 조회 및 상세 조회
2. 주문
    - 주문 상품에 대한 상태 조회
      - 주문 후 D+1에 배송중 
      - D+2일에 배송완료
    - 주문 취소
    - 주문 반품 (배송완료 된 상품 한정) 

</details>

<br>



## 🗂️ API 명세서
[petionary Postman API Documentation ](https://documenter.getpostman.com/view/22572063/2sA3kUG269)

<br>

## 🖼️ ERD
![erd](https://github.com/user-attachments/assets/ddce4fce-d4df-4fe6-a82e-928c97c47898)

<br>



## 📂 프로젝트 폴더 구조

```
├── java
│   └── org
│       └── pp
│           └── petionary
│               ├── PetionaryApplication.java
│               ├── global
│               │   ├── dto
│               │   │   ├── CommonResponseDto.java
│               │   │   ├── PaginationDto.java
│               │   │   └── ResultDto.java
│               │   ├── entity
│               │   │   └── BaseTime.java
│               │   ├── example
│               │   │   ├── ExampleController.java
│               │   │   ├── ExampleDto.java
│               │   │   └── ExampleService.java
│               │   ├── exception
│               │   │   ├── BadRequestException.java
│               │   │   └── NotFoundException.java
│               │   ├── redis
│               │   │   └── RedisConfig.java
│               │   ├── security
│               │   │   ├── config
│               │   │   │   └── SecurityConfig.java
│               │   │   └── jwt
│               │   │       ├── CustomLogoutFilter.java
│               │   │       ├── JWTUtil.java
│               │   │       ├── JwtFilter.java
│               │   │       └── LoginFilter.java
│               │   ├── service
│               │   │   └── CommonService.java
│               │   └── type
│               │       ├── ErrorCode.java
│               │       ├── ResponseStatus.java
│               │       └── SuccessCode.java
│               ├── order
│               │   ├── controller
│               │   │   └── OrderController.java
│               │   ├── dto
│               │   │   ├── OrderProductDto.java
│               │   │   ├── OrderRequestDto.java
│               │   │   ├── OrderResponseDto.java
│               │   │   └── OrderResponseListDto.java
│               │   ├── entity
│               │   │   ├── OrderProduct.java
│               │   │   └── Orders.java
│               │   ├── repository
│               │   │   ├── OrderProductRepository.java
│               │   │   └── OrderRepository.java
│               │   ├── service
│               │   │   └── OrderService.java
│               │   └── type
│               │       └── OrderStatus.java
│               ├── product
│               │   ├── controller
│               │   │   └── ProductController.java
│               │   ├── dto
│               │   │   ├── ProductDto.java
│               │   │   ├── ProductListDto.java
│               │   │   └── StockDto.java
│               │   ├── entity
│               │   │   ├── Product.java
│               │   │   ├── ProductImg.java
│               │   │   └── Stock.java
│               │   ├── repository
│               │   │   ├── ProductImgRepository.java
│               │   │   ├── ProductRepository.java
│               │   │   └── StockRepository.java
│               │   ├── service
│               │   │   ├── ProductService.java
│               │   │   └── StockService.java
│               │   └── type
│               │       ├── ProductStatus.java
│               │       └── Type.java
│               └── user
│                   ├── controller
│                   │   ├── ReissueController.java
│                   │   ├── UserController.java
│                   │   └── testController.java
│                   ├── dto
│                   │   ├── CustomUserDetails.java
│                   │   ├── PasswordUpdateDto.java
│                   │   ├── SignupRequestDto.java
│                   │   └── UserModifyDto.java
│                   ├── entity
│                   │   ├── RefreshEntity.java
│                   │   └── Users.java
│                   ├── repository
│                   │   ├── RefreshRepository.java
│                   │   └── UserRepository.java
│                   ├── service
│                   │   ├── CustomUserDetailsService.java
│                   │   ├── ReissueService.java
│                   │   └── UserService.java
│                   └── type
│                       └── UserRoleEnum.java
└── resources
    ├── application-db.yml
    ├── application.yml
    ├── static
    └── templates
```
