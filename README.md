DasiolMap-server
DasiolMap 프로젝트의 백엔드 서버입니다. 본 프로젝트는 사용자가 주변 상점을 검색하고, 리뷰를 작성하며, 즐겨찾기를 관리할 수 있는 기능을 제공하는 RESTful API 서버입니다.

📑 목차
프로젝트 소개

주요 기능

기술 스택

시작 가이드

Prerequisites

설치 및 실행

API 문서

데이터베이스 구조

팀원

📌 프로젝트 소개
DasiolMap은 지도 기반의 상점 정보 공유 서비스입니다. 사용자는 이 서비스를 통해 원하는 상점을 쉽게 찾고, 다른 사용자들과 리뷰 및 평점을 공유할 수 있습니다. 이 프로젝트는 DasiolMap 서비스의 핵심 비즈니스 로직을 처리하는 백엔드 API 서버입니다.

✨ 주요 기능
사용자 인증:

JWT(JSON Web Token)를 이용한 회원가입, 로그인, 로그아웃 기능

회원 정보 수정 및 탈퇴

상점:

상점 정보 등록, 조회, 수정 및 전체 목록 조회

리뷰:

상점에 대한 리뷰 및 평점 작성, 수정, 삭제

리뷰 작성 시 평점이 상점의 평균 평점에 반영

검색 (Elasticsearch):

키워드를 통한 상점 검색 (상점 이름, 주소, 위치)

평균 평점 높은 순으로 상점 정렬

북마크:

관심 있는 상점을 북마크에 추가 및 삭제

사진 및 태그:

리뷰에 사진 첨부

상점에 태그 추가 및 관리

🛠️ 기술 스택
언어: Java 17

프레임워크: Spring Boot 3.4.9

보안: Spring Security, JWT

데이터베이스: MariaDB (또는 H2), Spring Data JPA

검색 엔진: Elasticsearch

API 문서화: Swagger (SpringDoc OpenAPI)

빌드 도구: Gradle

기타: Lombok, dotenv-java

🚀 시작 가이드
Prerequisites
Java 17

Gradle

MariaDB (또는 H2)

Elasticsearch

설치 및 실행
레포지토리 클론:

Bash

git clone [레포지토리 주소]
cd dasiolmap-server/dasiolmap
환경변수 설정:
프로젝트 루트 경로(dasiolmap)에 .env 파일을 생성하고 아래 내용을 채워주세요.

DRIVER=org.mariadb.jdbc.Driver
URL=jdbc:mariadb://localhost:3306/your_database
DB_USER=your_username
PASSWD=your_password
JWT_SECRET_KEY=your_super_secret_key_for_jwt_generation

# 필요한 경우 OpenAI 관련 키도 추가
# OPEN_AI_MODEL=...
# OPEN_AI_KEY=...
# OPEN_AI_URL=...
H2 데이터베이스를 사용하려면 application.yaml 파일에서 mariadb 설정을 주석 처리하고 h2 설정을 활성화하세요.

애플리케이션 실행:

Bash

./gradlew bootRun
서버 접속:

애플리케이션은 http://localhost:8088에서 실행됩니다.

H2 콘솔: http://localhost:8088/h2-console

📖 API 문서
API 명세는 Swagger를 통해 자동으로 문서화됩니다. 애플리케이션 실행 후 아래 주소로 접속하여 확인할 수 있습니다.

Swagger UI: http://localhost:8088/swagger-ui/index.html

🗄️ 데이터베이스 구조
주요 엔티티와 관계는 다음과 같습니다.

UserEntity: 사용자 정보

DasiolStoreEntity: 상점 정보

DasiolReviewEntity: 리뷰 정보 (User, Store와 다대일 관계)

BookmarkEntity: 북마크 정보 (User, Store와 다대일 관계)

StorePhotoEntity: 리뷰에 첨부된 사진 정보

TagEntity, StoreTagEntity: 상점 태그 정보

(간단한 ER 다이어그램 이미지를 추가하면 좋습니다.)

👨‍👩‍👧‍👦 팀원
