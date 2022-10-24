FROM openjdk:8-jre
VOLUME /tmp
ADD ./target/hot-0.0.1.jar hot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/hot.jar"]