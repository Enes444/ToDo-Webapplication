# Verwendet ein Basis-Image mit OpenJDK 11
FROM openjdk:11-jre-slim

# Setzt das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiert das erzeugte JAR-File in den Container
COPY build/libs/*.jar app.jar

# Führt das JAR-File aus
ENTRYPOINT ["java", "-jar", "app.jar"]
