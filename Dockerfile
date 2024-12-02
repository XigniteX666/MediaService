#STEP 1 build the service
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /build

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn/

RUN ./mvnw dependency:go-offline

COPY src src/

RUN ./mvnw package


