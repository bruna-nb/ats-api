FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/recrutador-api-1.0.0-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]