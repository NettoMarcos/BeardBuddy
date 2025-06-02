FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install


FROM openjdk:21-jdk-slim

COPY --from=build target/beardbuddy-0.0.1-SNAPSHOT.jar.original app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]