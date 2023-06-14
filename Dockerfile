FROM maven:3-jdk-17-alpine AS build
# Copy folder in docker
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY target/demo-spring-k8s-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]