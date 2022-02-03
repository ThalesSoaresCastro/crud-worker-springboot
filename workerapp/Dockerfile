FROM openjdk:17-jdk-alpine

COPY target/workerapp-0.0.1-SNAPSHOT.jar app.jar

RUN ls

ENTRYPOINT ["java", "-jar", "/app.jar"]
