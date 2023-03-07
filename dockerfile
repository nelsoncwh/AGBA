FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17

RUN mkdir /app
COPY --from=build /app/target/wrapper-1.0.0.jar /app/wrapper.jar

EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=uat","-jar","/app/wrapper.jar"]