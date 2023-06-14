FROM maven:3.8.3-openjdk-17 AS build
# Copy folder in docker
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /opt/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]