FROM openjdk:17-alpine
RUN apk add --no-cache tzdata
ENV TZ=Asia/Hong_Kong
RUN mkdir /app
COPY ./target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar