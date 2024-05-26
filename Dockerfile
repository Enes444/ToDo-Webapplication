# Verwenden Sie ein Java-Basisimage für den Build
FROM openjdk:11-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

# Verwenden Sie ein schlankes Java-Basisimage für die Ausführung
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
