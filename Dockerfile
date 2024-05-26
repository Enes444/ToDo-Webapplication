# Verwenden Sie ein offizielles OpenJDK-Image als Basis für den Build
FROM openjdk:17-jdk-slim AS build

# Setzen Sie das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopieren Sie den Gradle-Wrapper und das build.gradle file
COPY build.gradle settings.gradle gradlew* ./
COPY gradle ./gradle

# Machen Sie den Gradle-Wrapper ausführbar
RUN chmod +x gradlew

# Installieren Sie die Gradle-Abhängigkeiten
RUN ./gradlew build || return 0

# Kopieren Sie den Rest des Anwendungscodes
COPY src ./src

# Bauen Sie die Anwendung
RUN ./gradlew build

# Verwenden Sie ein schlankes Runtime-Image
FROM openjdk:17-jdk-slim

# Kopieren Sie das erstellte Jar-File vom build-Image
COPY --from=build /app/build/libs/*.jar app.jar

# Port, den die Anwendung verwendet
EXPOSE 8081

# Starten Sie die Anwendung
ENTRYPOINT ["java", "-jar", "/app.jar"]
