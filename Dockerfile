# Verwendet ein Java-Basisimage
FROM openjdk:11-jre-slim

# Setzt das Arbeitsverzeichnis
WORKDIR /app

# Kopierf das erzeugte JAR-File in den Container
COPY build/libs/*.jar app.jar

# Führt  das JAR-File aus
ENTRYPOINT ["java", "-jar", "app.jar"]
