
FROM openjdk:17-jdk-alpine


# Setze das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere das erzeugte JAR-File in den Container
COPY build/libs/*.jar app.jar

# Führe das JAR-File aus
ENTRYPOINT ["java", "-jar", "app.jar"]