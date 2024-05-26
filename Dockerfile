# Verwendet  ein Java-Basisimage
FROM openjdk:11-jre-slim

# Setzt  das Arbeitsverzeichnis
WORKDIR /app

# Kopiert  das erzeugte JAR-File in den Container
COPY C:/Users/100040290/Downloads/webtech/webtech/build/libs/*.jar app.jar


# Führt  das JAR-File aus
ENTRYPOINT ["java", "-jar", "app.jar"]
