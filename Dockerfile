FROM openjdk:8-jdk-alpine

EXPOSE 8080 8080

ENV TZ=Asia/Seoul

VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

