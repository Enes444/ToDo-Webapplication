FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY build.gradle settings.gradle gradlew* ./
COPY gradle ./gradle
RUN chmod +x gradlew
RUN ./gradlew build -x test || return 0
COPY src ./src
RUN ./gradlew build -x test

FROM openjdk:17-jdk-slim
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]