#普通web应用
#FROM openjdk:8-jre
#VOLUME /tmp
#ADD ./target/hot-0.0.1.jar hot.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/hot.jar"]

# web应用，顺带部署一个ffmpeg
FROM openjdk:8-jdk-alpine
RUN apk add  --no-cache ffmpeg
VOLUME /tmp
ADD ./target/hot-0.0.1.jar hot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/hot.jar"]