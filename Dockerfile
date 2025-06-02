FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-21-jdk maven

COPY . .

RUN mvn clean package

RUN ls -l target/

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build /target/beardbuddy-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]