FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/candidato-api-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]