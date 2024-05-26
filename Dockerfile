# Verwenden Sie ein offizielles OpenJDK-Image als Basis
FROM openjdk:17-jdk-slim

# Argument für das Jar-File
ARG JAR_FILE=build/libs/*.jar

# Kopieren Sie das Jar-File in das Image
COPY ${JAR_FILE} app.jar

# Port, den die Anwendung verwendet
EXPOSE 8080

# Starten Sie die Anwendung
ENTRYPOINT ["java", "-jar", "/app.jar"]
