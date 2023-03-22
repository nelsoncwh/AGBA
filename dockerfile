# base image to build a JRE
FROM amazoncorretto:17.0.3-alpine as corretto-jdk
WORKDIR /app
COPY src /app/src
COPY pom.xml /app

# required for strip-debug to work
RUN apk add --no-cache binutils

FROM alpine:3.17.2
RUN apk add --no-cache tzdata
RUN $JAVA_HOME/bin/jlink \
    --verbose \
    --add-modules ALL-MODULE-PATH \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /customjre
    
ENV TZ=Asia/Hong_Kong
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

RUN mkdir /app
COPY --from=corretto-jdk /customjre $JAVA_HOME
COPY --from=build /app/target/wrapper-1.0.0.jar /app/wrapper.jar

EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=sit","-jar","/app/wrapper.jar"]