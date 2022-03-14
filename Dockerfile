FROM maven:3.5-jdk-11 AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
WORKDIR /usr/app
RUN mvn clean package

FROM gcr.io/distroless/java
COPY --from=build /usr/app/target/*.jar /usr/app/app.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","/usr/app/app.jar"]

