#base image
FROM openjdk:17-alpine
# 변수 설정
ARG JAR_FILE_PATH=build/libs/*.jar
# 빌드파일을 컨테이너로 복사
COPY ${JAR_FILE_PATH} app.jar
# jar 파일 실행
ENTRYPOINT ["java", "-jar", "app.jar"]