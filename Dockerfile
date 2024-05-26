FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle ./
COPY settings.gradle ./
COPY src ./src

RUN chmod +x gradlew

RUN ./gradlew clean build

COPY build/libs/*.jar app.jar

ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
