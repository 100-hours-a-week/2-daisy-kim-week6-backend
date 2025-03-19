# 📌 프로젝트 개요
### "아무말 대잔치" 커뮤니티 백엔드 구현

### 🔗 프론트엔드 레포지토리 링크
https://github.com/100-hours-a-week/2-daisy-kim-week6-frontend

### 🛠️ 기술 스택
![SpringBoot](https://img.shields.io/badge/SpringBoot-%2369AD3C.svg?style=for-the-badge&logo=spring&logoColor=%23ffffff)
![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=white)

<br>

# 📌 폴더 구조
```
┗ 📂 backend
  ┗ 📂 config
    ┣ 📜 CorsConfig //프론트엔드 연결 세팅
    ┣ 📜 EncryptionConfig //비밀번호 Security 설정
    ┗ 📜 WebMvcConfig //이미지 경로 매핑 설정
  ┗ 📂 controller
  ┗ 📂 dto
    ┗ 📂 board
    ┣ 📂 comment
    ┣ 📂 user
    ┗ 📜 ResponseDto
  ┣ 📂 entity
  ┣ 📂 repository //interface
  ┗ 📂 service
```

# 📌 ERD
![Copy of KTB-Community](https://github.com/user-attachments/assets/6ac0e7f5-73fa-41c2-819f-a3078c339b86)

# 📌 구현 기능
1. 회원가입
2. 로그인
3. 게시물 목록 조회
4. 게시물 작성
5. 게시물 수정
6. 게시물 삭제
7. 답글 작성
8. 답글 수정
9. 답글 삭제
10. 회원 정보 수정
11. 회원 탈퇴

<br>

# 📌 회고
### 1. 프로젝트 생성 방식
1. 깃허브에 레포지토리 생성 후 클론하여 프로젝트 생성
2. 프로젝트 생성 후 깃허브에 업로드<br>
두 가지 방식 중 프론트엔드 프로젝트 생성 시 1번의 방식을 사용합니다.<br>
인텔리제의 경우 1번의 방식으로 진행할 경우 새 프로젝트를 생성하면 클론한 폴더에 생성되는 것이 아닌 깃허브와 연결되지 않는 새 프로젝트 폴더가 생성되었습니다.<br>
프로젝트 생성이 아닌 모듈 생성으로 진행해야 한다는 점을 알 수 있었습니다.

### 2. 보안 정보 관리
React에서는 .env 파일에 중요한 정보를 넣는 것이 일반적이었습니다.<br>
인텔리제에서 .env에 작성한 DB 정보가 잘 불러와지지 않았습니다.<br>
이후 인텔리제 자체에 환경 변수를 저장하는 기능을 아놀디에게 배워 적용하였습니다.

### 3. Postman 설치
Django에서는 api를 테스트할 수 있는 docs를 지원해 주었던 것으로 기억합니다.
React 구현 시작 전 api 동작 여부를 확인하기 위해 Postman을 설치하고 확인하였습니다.<br>

### 4. 로그인 상태 유지에 대한 고민 (인증 방식)
HttpSession을 사용하였습니다.<br>
stateful을 유지해야 하기 때문에 jwt를 사용할까 고민하였지만 익숙한 방식으로 빠른 구현을 택했습니다.<br>
프론트엔드에서 api 세팅 시 어떤 설정이 필요한지 이번 백엔드 프로젝트를 통해 알 수 있었습니다.<br>
특히 withCredentials : true가 필요한 경우를 알 수 있었습니다.<br>
이후 포스트맨에서 로그인 성공 시 쿠키를 반환하는 것을 확인할 수 있었습니다.<br>
jwt로 방식을 바꾸어 적용해 보고 싶다고 생각하였습니다.

### 5. 회원가입 시 DB에 저장되는 비밀번호의 암호화
처음에 암호화 없이 구현하였습니다.<br>
ERD를 그릴 때도 고려하지 못하였다가 구현이 마무리된 후 제이의 피드백을 받았습니다.<br>
비밀번호 암호화가 혹시 없냐는 질문을 받고 Bcrypt를 사용하였습니다.<br>
이는 로그인, 회원가입, 비밀번호 수정에 필요합니다. <br>
암호화를 적용하며 Configuration 어노테이션을 사용하였습니다.

### 6. 탈퇴 및 게시글 삭제 시 FK가 null일 수 없는 문제 상황
처음 DB를 카멜 케이스로 ddl를 작성하였습니다.<br>
하지만 Entity를 통해 생성되는 테이블은 스네이크 형식이었습니다.<br>
DB 충돌이 발생하였고 전부 지운 후 JPA로 엔티티를 생성하도록 하였으며 프론트엔드에서 카멜이 익숙한 반면 DB에서는 스네이크를 익숙하게 여겨야 함을 알았습니다.<br>
하지만 자동 생성된 DB를 사용하다 보니 의도와 다르게 생성되는 부분이 있었습니다.<br>
그 중 가장 크게 와닿은 부분이 회원 탈퇴 시 다른 테이블의 FK가 null이 되는 문제를 마주하였습니다.<br>
`cascade = CascadeType.REMOVE`를 추가하였으며 이는 DB에 자동 업데이트되는 사항이 아님을 알게 되었고 DataGrip의 콘솔 창을 이용하여 sql문을 통해 FK 속성을 새로 설정하였습니다.<br>


### 7. 이미지 저장 방식에 대한 조사
React를 통해 작업하던 중 

### 8. Builder 패턴 일부 적용

### 9. 테스트 케이스/TDD의 중요성
get, post api만 postman을 통해 확인하였습니다.<br>
하지만 react에서 오류가 발생하였고 프론트엔드의 문제인 줄 알았으나 프론트엔드에서 테스트하면서 6번부터 백엔드 문제를 다소 많이 발견할 수 있었습니다.<br>
이것이 백엔드 재미의 시작이었습니다. 
프론트엔드와 백엔드 연결에 문제가 생겼을 때 어디의 문제인지 알기 어려웠습니다.
백엔드를 때문에 더욱 하고 싶었는데 예외 처리 하나하나 고려해야 함을 깨닫고 테스트 케이스의 중요성을 알게 되었습니다.

### 10. 파일별 역할의 명확성

