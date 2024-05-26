# Verwende ein Basis-Image mit OpenJDK 11
FROM openjdk:11-jre-slim

# Setze das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere das erzeugte JAR-File in den Container
COPY webtech/build/libs/*.jar app.jar

# Führe das JAR-File aus
ENTRYPOINT ["java", "-jar", "app.jar"]
